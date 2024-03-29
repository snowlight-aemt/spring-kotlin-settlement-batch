package me.snowlight.springsettlementbatch.core.job.purchaseconfired

import jakarta.persistence.EntityManager
import me.snowlight.springsettlementbatch.core.job.purchaseconfired.claim.ClaimDailySettlementItemWriter
import me.snowlight.springsettlementbatch.core.job.purchaseconfired.claim.ClaimDailySettlementProcessor
import me.snowlight.springsettlementbatch.core.job.purchaseconfired.daily.DailySettlementItemWriter
import me.snowlight.springsettlementbatch.core.job.purchaseconfired.daily.DailySettlementProcessor
import me.snowlight.springsettlementbatch.core.job.purchaseconfired.delivery.PurchaseCompletedProcessor
import me.snowlight.springsettlementbatch.core.job.purchaseconfired.delivery.PurchaseConfirmedWriter
import me.snowlight.springsettlementbatch.core.listener.PurchaseConfirmedChunkListener
import me.snowlight.springsettlementbatch.domain.entity.claim.ClaimItem
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import me.snowlight.springsettlementbatch.infrastructure.database.repository.OrderItemRepository
import me.snowlight.springsettlementbatch.infrastructure.database.repository.SettlementDailyRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.time.ZonedDateTime
import javax.sql.DataSource

@Configuration
//@EnableBatchProcessing        // BATCH 5 이후 부터 필요 없음 따라서, 주석 처리
@EnableTransactionManagement
class PurchaseConfirmedJobConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    @Qualifier("deliveryCompletedJpaItemReader")
    private val deliveryCompletedJpaItemReader: JpaPagingItemReader<OrderItem>,
    @Qualifier("dailySettlementJpaItemReader")
    private val dailySettlementJpaItemReader: JpaPagingItemReader<OrderItem>,
    @Qualifier("claimDailySettlementJpaItemReader")
    private val claimDailySettlementJpaItemReader: JpaPagingItemReader<ClaimItem>,
    private val orderItemRepository: OrderItemRepository,
    private val settlementDailyRepository: SettlementDailyRepository,
    private val dataSource: DataSource,
    private val entityManager: EntityManager,
) {
    val JOB_NAME = "purchaseConfirmedJob"
    val chunkSize = 500

    @Bean
    fun purchaseConfirmedJob(): Job {
        return JobBuilder(JOB_NAME, jobRepository)
            .start(purchaseConfirmedJobStep())  // SimpleJobBuilder
            .next(dailySettlementJobStep())
            .next(claimDailySettlementJobStep())
            .build()
    }

    @Bean
    @JobScope
    fun purchaseConfirmedJobStep(): Step {
        return StepBuilder("${JOB_NAME}_step", jobRepository)
            .chunk<OrderItem, OrderItem>(chunkSize, transactionManager) // SimpleStepBuilder // TODO 공부 필요 chunk 방식 말고 다른 방식이 있다?
            .reader(deliveryCompletedJpaItemReader)                   // TODO reader, processor, writer 각각의 기능에 대해서 공부
            .processor(purchaseCompletedProcessor())
            .writer(purchaseConfirmedWriter())
//            .writer(purchaseConfirmedJdbcItemWriter())
//            .writer(purchaseConfirmedJpaItemWriter())
            .listener(PurchaseConfirmedChunkListener())
            .build()
    }

    @Bean
    fun purchaseCompletedProcessor(): ItemProcessor<OrderItem, OrderItem> {
        return PurchaseCompletedProcessor();
    }

    @Bean
    fun purchaseConfirmedWriter(): PurchaseConfirmedWriter {
        return PurchaseConfirmedWriter(orderItemRepository);
    }

    // TODO JPA 영속성에 이슈가 있다.
    @Bean
    fun purchaseConfirmedJdbcItemWriter(): JdbcBatchItemWriter<OrderItem> {
        val writer = JdbcBatchItemWriterBuilder<OrderItem>()
            .dataSource(dataSource)
            .sql("""
                UPDATE order_item 
                   SET purchase_confirmed_at = :purchaseConfirmedAt,
                       updated_at = :updatedAt
                 WHERE order_item_no = :id
            """.trimIndent())
            .itemSqlParameterSourceProvider {
                val parameterSource = MapSqlParameterSource()
                parameterSource.addValue("purchaseConfirmedAt", ZonedDateTime.now())
                parameterSource.addValue("updatedAt", ZonedDateTime.now())
                parameterSource.addValue("id", it.id)
            }
            .build()
        return writer
    }

    @Bean
    fun purchaseConfirmedJpaItemWriter(): JpaItemWriter<OrderItem> {
        val writer = JpaItemWriter<OrderItem>()
        writer.setEntityManagerFactory(this.entityManager.entityManagerFactory)
        return writer
    }

    @Bean
    @JobScope
    fun dailySettlementJobStep(): Step {
        return StepBuilder("${JOB_NAME}_dailySettlement_step", jobRepository)
            .chunk<OrderItem, SettlementDaily>(chunkSize, transactionManager)
            .reader(dailySettlementJpaItemReader)
            .processor(dailySettlementProcessor())
            .writer(dailySettlementItemWriter())
            .build()
    }

    @Bean
    fun dailySettlementProcessor(): DailySettlementProcessor {
        return DailySettlementProcessor();
    }

    @Bean
    fun dailySettlementItemWriter(): DailySettlementItemWriter {
        return DailySettlementItemWriter(settlementDailyRepository);
    }

    @Bean
    @JobScope
    fun claimDailySettlementJobStep(): Step {
        return StepBuilder("${JOB_NAME}_claimDailySettlement_step", jobRepository)
            .chunk<ClaimItem, SettlementDaily>(chunkSize, transactionManager)
            .reader(claimDailySettlementJpaItemReader)
            .processor(claimDailySettlementProcessor())
            .writer(claimDailySettlementItemWriter())
            .build()
    }

    @Bean
    fun claimDailySettlementProcessor(): ClaimDailySettlementProcessor {
        return ClaimDailySettlementProcessor();
    }

    @Bean
    fun claimDailySettlementItemWriter(): ClaimDailySettlementItemWriter {
        return ClaimDailySettlementItemWriter(settlementDailyRepository);
    }
}