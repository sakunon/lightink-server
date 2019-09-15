package org.lightink.reader.utils

import com.google.common.base.Throwables
import com.google.gson.Gson
import io.vertx.core.Handler

import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.mysqlclient.preparedQueryAwait
import io.vertx.mysqlclient.MySQLPool
import io.vertx.sqlclient.Tuple
import mu.KotlinLogging
import org.lightink.reader.entity.BasicError
import java.net.URLDecoder
import java.net.URLEncoder


/**
 * @Auther: zoharSoul
 * @Date: 2019-05-21 16:17
 * @Description:
 */
private val logger = KotlinLogging.logger {}

val gson = Gson()

fun RoutingContext.success(any: Any?) {
    val toJson: String = if (any is JsonObject) {
        any.toString()
    } else {
        gson.toJson(any)
    }
    this.response()
            .putHeader("content-type", "application/json")
            .end(toJson)
}

fun RoutingContext.error(throwable: Throwable) {
    val path = URLDecoder.decode(this.request().absoluteURI())
    val basicError = BasicError(
            "Internal Server Error",
            throwable.toString(),
            throwable.message.toString(),
            path,
            500,
            System.currentTimeMillis()
    )

    val errorJson = gson.toJson(basicError)
    logger.error("Internal Server Error", throwable)
    logger.error { errorJson }

    //插入失败打点
    try {
        val code = this.pathParam("code")
        val name = this.pathParam("name")

        SpringContextUtils.getBean(MySQLPool::class.java).preparedQuery("insert b_history(code, name, link, type, status, errorinfo) values (?, ?, ?, ?, ?, ?) ",
                Tuple.of(code, name, path, "failed", 0, Throwables.getStackTraceAsString(throwable))) {
        }

    } catch (e: Exception) {
        logger.error("insert error log failed", e)
    }

    this.response()
            .putHeader("content-type", "application/json")
            .setStatusCode(500)
            .end(errorJson)
}

//fun Single<*>.subscribe(routingContext: RoutingContext) {
//
//    this.subscribe({ onSuccess ->
//        routingContext.success(onSuccess)
//    }, { error ->
//        error.printStackTrace()
//        routingContext.error(error)
//    })
//
//}
//
//fun Maybe<*>.subscribe(routingContext: RoutingContext) {
//
//    this.subscribe({ onSuccess ->
//        routingContext.success(onSuccess)
//    }, { error ->
//        error.printStackTrace()
//        routingContext.error(error)
//    })
//
//}