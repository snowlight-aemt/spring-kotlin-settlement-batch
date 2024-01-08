package me.snowlight.springsettlementbatch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableBatchProcessing
class SpringSettlementBatchApplication

fun main(args: Array<String>) {
    runApplication<SpringSettlementBatchApplication>(*args)
}
