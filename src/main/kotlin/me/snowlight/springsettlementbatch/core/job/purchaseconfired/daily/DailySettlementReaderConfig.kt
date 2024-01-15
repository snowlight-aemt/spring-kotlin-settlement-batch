package me.snowlight.springsettlementbatch.core.job.purchaseconfired.daily

import jakarta.persistence.EntityManager
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Configuration
class DailySettlementReaderConfig(
    private val entityManager: EntityManager,
) {
    val chunkSize: Int = 500
    val startDateTime: ZonedDateTime = ZonedDateTime.of(
        LocalDate.now(),
        LocalTime.MIN,
        ZoneId.of("Asia/Seoul")
    )

    val endDateTime: ZonedDateTime = ZonedDateTime.of(
        LocalDate.now(),
        LocalTime.MAX,
        ZoneId.of("Asia/Seoul")
    )

    @Bean
    fun dailySettlementJpaItemReader(): JpaPagingItemReader<OrderItem> {
        return JpaPagingItemReaderBuilder<OrderItem>()
            .name("dailySettlementJpaItemReader")
            .entityManagerFactory(this.entityManager.entityManagerFactory)
            .pageSize(chunkSize)
            .queryProvider(CustomPurchaseConfirmedItemQueryProvider(startDateTime, endDateTime))
            .build()
    }
}