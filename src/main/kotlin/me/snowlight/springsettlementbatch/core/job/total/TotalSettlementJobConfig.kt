package me.snowlight.springsettlementbatch.core.job.total

import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementTotal
import me.snowlight.springsettlementbatch.infrastructure.database.repository.SettlementTotalRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

//@Configuration
@EnableTransactionManagement
class TotalSettlementJobConfig(
    private val jobRepository: JobRepository,
    private val transactionManager: PlatformTransactionManager,
    @Qualifier("totalSettlementJpaItemReader")
    private val totalSettlementJpaItemReader: JpaPagingItemReader<SummingSettlementResponse>,
    private val settlementTotalRepository: SettlementTotalRepository,
) {
    // 집계를 위한 JOB
    private val JOB_NAME = "totalSettlementJob"
    private val chunkSize = 500


    @Bean
    fun totalSettlementJob(): Job {
        return JobBuilder(JOB_NAME, jobRepository)
            .start(totalSettlementJobStep())
            .build();
    }

    @Bean
    @JobScope
    fun totalSettlementJobStep(): Step {
        return StepBuilder("${JOB_NAME}_step", jobRepository)
            .chunk<SummingSettlementResponse, SettlementTotal>(chunkSize, transactionManager)
            .reader(totalSettlementJpaItemReader)
            .processor(totalSettlementProcessor())
            .writer(totalSettlementItemWriter())
            .build()
    }

    @Bean
    fun totalSettlementProcessor() = TotalSettlementItemProcessor()

    @Bean
    fun totalSettlementItemWriter() = TotalSettlementItemWriter(settlementTotalRepository);
}