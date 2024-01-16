package me.snowlight.springsettlementbatch.core.job.purchaseconfired.daily

import me.snowlight.springsettlementbatch.domain.collection.CommissionAmountCalculator
import me.snowlight.springsettlementbatch.domain.collection.PgSalesAmountCalculator
import me.snowlight.springsettlementbatch.domain.collection.PositiveDailySettlementCollection
import me.snowlight.springsettlementbatch.domain.collection.TaxCalculator
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor
import java.math.BigDecimal
import java.time.LocalDate

class DailySettlementProcessor: ItemProcessor<OrderItem, SettlementDaily> {
    override fun process(orderItem: OrderItem): SettlementDaily {
        return PositiveDailySettlementCollection(orderItem).getSettlementDaily()
    }
}