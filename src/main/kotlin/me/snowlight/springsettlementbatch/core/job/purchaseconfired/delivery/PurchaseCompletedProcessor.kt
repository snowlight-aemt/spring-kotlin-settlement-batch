package me.snowlight.springsettlementbatch.core.job.purchaseconfired.delivery

import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import org.springframework.batch.item.ItemProcessor
import org.springframework.context.annotation.Configuration
import java.time.ZonedDateTime

class PurchaseCompletedProcessor: ItemProcessor<OrderItem, OrderItem> {
    override fun process(item: OrderItem): OrderItem {
        println(">> Processor")
        println(item.toString())
        return item.copy(id = item.id, purchaseConfirmedAt = ZonedDateTime.now())
    }
}