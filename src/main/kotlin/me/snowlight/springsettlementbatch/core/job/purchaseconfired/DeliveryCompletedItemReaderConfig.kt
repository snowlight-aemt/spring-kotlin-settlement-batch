package me.snowlight.springsettlementbatch.core.job.purchaseconfired

import jakarta.persistence.EntityManager
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import me.snowlight.springsettlementbatch.infrastructure.database.repository.OrderItemRepository
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Configuration
class DeliveryCompletedItemReaderConfig(
    private val entityManager: EntityManager,
) {
    val chunkSize: Int = 500
    val startDateTime: ZonedDateTime = ZonedDateTime.of(
        LocalDate.now().minusDays(1),
        LocalTime.MIN,
        ZoneId.of("Asia/Seoul")
    )

    val endDateTime: ZonedDateTime = ZonedDateTime.of(
        LocalDate.now().plusDays(1),
        LocalTime.MIN,
        ZoneId.of("Asia/Seoul")
    )

    @Bean
    fun deliveryCompletedJpaItemReader(orderItemRepository: OrderItemRepository): JpaPagingItemReader<OrderItem> {
        val queryProvider = DeliveryConfirmedJpaQueryProvider(this.startDateTime, this.endDateTime)

        return JpaPagingItemReaderBuilder<OrderItem>()
            .name("deliveryCompletedJpaItemReader")
            .entityManagerFactory(this.entityManager.entityManagerFactory)
            .pageSize(this.chunkSize)
            .queryProvider(queryProvider)
            .build()
    }
}