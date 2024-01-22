package me.snowlight.springsettlementbatch.domain

interface OrderPurchaseConfirmedExecutor {
    fun confirmed(orderNo: Long)
}
