package me.snowlight.springsettlementbatch.core.job.purchaseconfired.daily

import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import me.snowlight.springsettlementbatch.infrastructure.database.repository.SettlementDailyRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class DailySettlementItemWriter(
    private val settlementDailyRepository: SettlementDailyRepository,
): ItemWriter<SettlementDaily> {
    override fun write(chunk: Chunk<out SettlementDaily>) {
        println("Settlement Item Writer")
        chunk.items.forEach { settlementDailyRepository.save(it) }
    }

}