package me.snowlight.springsettlementbatch.core.job.purchaseconfired.daily

import me.snowlight.springsettlementbatch.domain.collection.PositiveDailySettlementCollection
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor

class DailySettlementProcessor: ItemProcessor<OrderItem, SettlementDaily> {
    override fun process(orderItem: OrderItem): SettlementDaily {
        return PositiveDailySettlementCollection(orderItem).getSettlementDaily()
    }
}