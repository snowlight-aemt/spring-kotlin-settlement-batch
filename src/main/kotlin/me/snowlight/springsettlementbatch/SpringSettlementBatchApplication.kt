package me.snowlight.springsettlementbatch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableBatchProcessing            // BATCH 5 이후 부터 필요
class SpringSettlementBatchApplication

fun main(args: Array<String>) {
    runApplication<SpringSettlementBatchApplication>(*args)
}
