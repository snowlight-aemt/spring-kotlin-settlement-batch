package me.snowlight.springsettlementbatch.core.job.purchaseconfired.claim

import me.snowlight.springsettlementbatch.domain.collection.CommissionAmountCalculator
import me.snowlight.springsettlementbatch.domain.collection.NegativeDailySettlementCollection
import me.snowlight.springsettlementbatch.domain.collection.PgSalesAmountCalculator
import me.snowlight.springsettlementbatch.domain.collection.TaxCalculator
import me.snowlight.springsettlementbatch.domain.entity.claim.ClaimItem
import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor
import java.math.BigDecimal
import java.time.LocalDate

class ClaimDailySettlementProcessor: ItemProcessor<ClaimItem, SettlementDaily> {
    override fun process(item: ClaimItem): SettlementDaily {
        val negativeDailySettlementCollection = NegativeDailySettlementCollection(item)
        return negativeDailySettlementCollection.getSettlementDaily()
    }
}