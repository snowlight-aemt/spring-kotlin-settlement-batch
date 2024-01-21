package me.snowlight.springsettlementbatch.core.job.purchaseconfired.claim

import jakarta.persistence.EntityManager
import me.snowlight.springsettlementbatch.domain.entity.claim.ClaimItem
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClaimDailySettlementReaderConfig(
    private val entityManager: EntityManager
) {
    val chunkSize: Int = 500

    @Bean
    fun claimDailySettlementJpaItemReader(): JpaPagingItemReader<ClaimItem> {
        return JpaPagingItemReaderBuilder<ClaimItem>()
            .name("claimDailySettlementJpaItemReader")
            .entityManagerFactory(this.entityManager.entityManagerFactory)
            .pageSize(chunkSize)
            .queryProvider(CustomClaimItemQueryProvider())
            .build()
    }
}