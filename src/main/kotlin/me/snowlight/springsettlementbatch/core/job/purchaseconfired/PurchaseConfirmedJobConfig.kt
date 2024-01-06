package me.snowlight.springsettlementbatch.core.job.purchaseconfired

import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.data.RepositoryItemReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
class PurchaseConfirmedJobConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    @Qualifier("deliveryCompletedJpaItemReader")
    private val repositoryItemReader: RepositoryItemReader<OrderItem>,
) {
    val JOB_NAME = "purchaseConfirmedJob"
    val chunkSize = 500

    @Bean
    fun purchaseConfirmedJob(): Job {
        return JobBuilder(JOB_NAME, jobRepository)
            .start(purchaseConfirmedJobStep())  // SimpleJobBuilder
            .build()
    }

    @Bean
    @JobScope
    fun purchaseConfirmedJobStep(): Step {
        return StepBuilder("${JOB_NAME}_STEP", jobRepository)
            .chunk<OrderItem, OrderItem>(chunkSize, transactionManager) // SimpleStepBuilder // TODO 공부 필요 chunk 방식 말고 다른 방식이 있다?
            .reader(repositoryItemReader)     // TODO reader, processor, writer 각각의 기능에 대해서 공부
//            .processor()
//            .writer()
            .build()
    }
}