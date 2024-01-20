package me.snowlight.springsettlementbatch.core.job.purchaseconfired.claim

import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import me.snowlight.springsettlementbatch.infrastructure.database.repository.SettlementDailyRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter

class ClaimDailySettlementItemWriter(
    private val settlementDailyRepository: SettlementDailyRepository,
): ItemWriter<SettlementDaily> {
    override fun write(chunk: Chunk<out SettlementDaily>) {
        println("Claim Daily Settlement Item Writer")
        chunk.items.forEach { settlementDailyRepository.save(it) }
    }

}