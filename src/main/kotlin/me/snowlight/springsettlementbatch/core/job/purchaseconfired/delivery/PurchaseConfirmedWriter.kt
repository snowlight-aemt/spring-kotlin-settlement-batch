package me.snowlight.springsettlementbatch.core.job.purchaseconfired.delivery

import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import me.snowlight.springsettlementbatch.infrastructure.database.repository.OrderItemRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter

//@Component
class PurchaseConfirmedWriter(
    private val orderItemRepository: OrderItemRepository,
): ItemWriter<OrderItem> {
    override fun write(chunk: Chunk<out OrderItem>) {
        chunk.items.forEach { orderItemRepository.save(it) }
    }
}