package me.snowlight.springsettlementbatch.core.job.purchaseconfired.delivery

import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import me.snowlight.springsettlementbatch.infrastructure.database.repository.OrderItemRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter

class PurchaseConfirmedWriter(
    private val orderItemRepository: OrderItemRepository,
): ItemWriter<OrderItem> {
    override fun write(chunk: Chunk<out OrderItem>) {
        // 이슈 발생 시 listener 호출
        chunk.items.forEach { orderItemRepository.save(it) }
    }
}