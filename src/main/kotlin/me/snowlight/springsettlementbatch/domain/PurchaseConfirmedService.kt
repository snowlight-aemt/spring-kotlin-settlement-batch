package me.snowlight.springsettlementbatch.domain

import org.springframework.stereotype.Service

@Service
class PurchaseConfirmedService(
    private val executor: OrderPurchaseConfirmedExecutor,
) {
    @Throws(Exception::class)
    fun complete(orderNo: Long) = executor.confirmed(orderNo)
}
