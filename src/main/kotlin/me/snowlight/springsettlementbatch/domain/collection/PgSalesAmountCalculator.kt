package me.snowlight.springsettlementbatch.domain.collection

import java.math.BigDecimal

class PgSalesAmountCalculator(private val orderItemSnapshot: PgSalesAmountMaterial) {
    fun getPgSaleAmount(): BigDecimal {
        val price = orderItemSnapshot.sellPrice ?: BigDecimal.ZERO
        return price
            .subtract(orderItemSnapshot.promotionAmount)
            .subtract(orderItemSnapshot.mileageUsageAmount)
    }
}
