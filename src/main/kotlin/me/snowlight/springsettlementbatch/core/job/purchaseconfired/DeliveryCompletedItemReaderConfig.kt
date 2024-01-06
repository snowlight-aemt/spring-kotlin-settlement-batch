package me.snowlight.springsettlementbatch.core.job.purchaseconfired

import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import me.snowlight.springsettlementbatch.infrastructure.database.repository.OrderItemRepository
import org.springframework.batch.item.data.RepositoryItemReader
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Configuration
class DeliveryCompletedItemReaderConfig {
    val chunkSize: Int = 500
    val startDateTime: ZonedDateTime = ZonedDateTime.of(
        LocalDate.now(),
        LocalTime.MIN,
        ZoneId.of("Asia/Seoul")
    )

    val endDateTime: ZonedDateTime = ZonedDateTime.of(
        LocalDate.now().plusDays(1),
        LocalTime.MIN,
        ZoneId.of("Asia/Seoul")
    )

    @Bean
    fun deliveryCompletedJpaItemReader(orderItemRepository: OrderItemRepository): RepositoryItemReader<OrderItem> {
        return RepositoryItemReaderBuilder<OrderItem>()
            .name("deliveryCompletedJpaItemReader")
            .repository(orderItemRepository)
            .methodName("findByShippedCompleteAtBetween")
            .arguments(this.startDateTime, this.endDateTime)
            .pageSize(this.chunkSize)
            .sorts(mapOf("shippedCompleteAt" to Sort.Direction.ASC))
            .build()
    }
}