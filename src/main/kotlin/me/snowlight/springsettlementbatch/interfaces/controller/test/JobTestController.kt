package me.snowlight.springsettlementbatch.interfaces.controller.test

import me.snowlight.springsettlementbatch.core.launcher.PurchaseConfirmedRunner
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class JobTestController(
    private val purchaseConfirmedRunner: PurchaseConfirmedRunner
){
    @GetMapping("/settlement/daily")
    @Throws(Exception::class)
    fun runDailyJob() {
        purchaseConfirmedRunner.runJob()
    }

    @GetMapping("/settlement/total")
    @Throws(Exception::class)
    fun runTotalJob() {
    }
}