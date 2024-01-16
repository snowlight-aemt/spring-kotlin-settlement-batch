package me.snowlight.springsettlementbatch.domain.collection

import me.snowlight.springsettlementbatch.domain.entity.order.OrderItemSnapshot
import java.math.BigDecimal

class PgSalesAmountCalculator(private val orderItemSnapshot: OrderItemSnapshot) {
    fun getPgSaleAmount(): BigDecimal {
        val price = orderItemSnapshot.sellPrice ?: BigDecimal.ZERO
        return price
            .subtract(orderItemSnapshot.promotionAmount)
            .subtract(orderItemSnapshot.mileageUsageAmount)
    }
}
