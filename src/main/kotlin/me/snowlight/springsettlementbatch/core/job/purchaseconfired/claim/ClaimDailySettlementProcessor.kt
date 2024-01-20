package me.snowlight.springsettlementbatch.core.job.purchaseconfired.claim

import me.snowlight.springsettlementbatch.domain.collection.NegativeDailySettlementCollection
import me.snowlight.springsettlementbatch.domain.entity.claim.ClaimItem
import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor

class ClaimDailySettlementProcessor: ItemProcessor<ClaimItem, SettlementDaily> {
    override fun process(item: ClaimItem): SettlementDaily {
        val negativeDailySettlementCollection = NegativeDailySettlementCollection(item)
        return negativeDailySettlementCollection.getSettlementDaily()
    }
}