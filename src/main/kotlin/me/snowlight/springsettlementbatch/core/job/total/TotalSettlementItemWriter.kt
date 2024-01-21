package me.snowlight.springsettlementbatch.core.job.total

import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementTotal
import me.snowlight.springsettlementbatch.infrastructure.database.repository.SettlementTotalRepository
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter

class TotalSettlementItemWriter(
    private val settlementTotalRepository: SettlementTotalRepository,
): ItemWriter<SettlementTotal> {
    override fun write(chunk: Chunk<out SettlementTotal>) {
        chunk.forEach { settlementTotalRepository.save(it) }
    }
}