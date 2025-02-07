package org.lightink.reader.service

import okhttp3.HttpUrl.Companion.toHttpUrl
import org.junit.Test
import org.junit.runner.RunWith
import org.lightink.reader.utils.url
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @Date: 2019-07-19 19:39
 * @Description:
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class BookServiceTest {

    @Autowired
    private lateinit var bookService: BookService

//    @Test
//    fun search() {
//        val countDownLatch = CountDownLatch(1)
//
//        mainService.search("", "", "人道天堂")
//                .subscribe({ t ->
//                    println(t.toString())
//                    countDownLatch.countDown()
//                }, { e ->
//                    e.printStackTrace()
//
//                })
//
//        countDownLatch.await()
//    }
//
//    @Test
//    fun details() {
//
//
//        val countDownLatch = CountDownLatch(1)
//
//        mainService.search("", "", "人道天堂")
//                .flatMap {
//                    return@flatMap mainService.details("", "", it.first()["link"]!!)
//                }
//                .subscribe({ t ->
//                    println(t.toString())
//                    countDownLatch.countDown()
//                }, { e ->
//                    e.printStackTrace()
//
//                })
//
//        countDownLatch.await()
//
//    }
//
//
//    @Test
//    fun content() {
//
//        val countDownLatch = CountDownLatch(1)
//
//        mainService.search("", "", "人道天堂")
//                .flatMap {
//                    return@flatMap mainService.details("", "", it.first()["link"]!!)
//                }
//                .flatMap {
//                    return@flatMap mainService.content("", "", "http://www.daocaorenshuwu.com/book/rendaotiantang/585721.html")
//                }
//                .subscribe({ t ->
//                    println(t.toString())
//                    countDownLatch.countDown()
//                }, { e ->
//                    e.printStackTrace()
//
//                })
//
//        countDownLatch.await()
//
//    }

    @Test
    fun urlTest() {
        println("//www.daocaorenshuwu.com/book/jianlai/1925220.html".url().toHttpUrl().encodedPath)
    }
}

