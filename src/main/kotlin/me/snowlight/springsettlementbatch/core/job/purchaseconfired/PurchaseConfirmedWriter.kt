package me.snowlight.springsettlementbatch.core.job.purchaseconfired

import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import me.snowlight.springsettlementbatch.infrastructure.database.repository.OrderItemRepository
import org.hibernate.annotations.Comment
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class PurchaseConfirmedWriter(
    private val orderItemRepository: OrderItemRepository,
): ItemWriter<OrderItem> {
    override fun write(chunk: Chunk<out OrderItem>) {
        println(">> Writer")
        chunk.items.forEach { orderItemRepository.save(it) }
    }
}