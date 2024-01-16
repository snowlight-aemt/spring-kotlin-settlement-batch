package me.snowlight.springsettlementbatch.domain.collection

import me.snowlight.springsettlementbatch.domain.entity.order.OrderItemSnapshot
import java.math.BigDecimal

class CommissionAmountCalculator(private val orderItemSnapshot: OrderItemSnapshot) {
    fun getCommissionAmount(): BigDecimal {
        val seller = orderItemSnapshot.seller
        val price = orderItemSnapshot.sellPrice ?: BigDecimal.ZERO

        val marginAmount = price.subtract(orderItemSnapshot.supplyPrice)
        val commission = seller.commission ?: 0

        return getCalculate(marginAmount, commission)
    }

    private fun getCalculate(marginAmount: BigDecimal, commission: Int): BigDecimal {
        if (commission == 0)
            return BigDecimal.ZERO

        val rate = commission.div(100).toBigDecimal()
        return marginAmount.multiply(rate)
    }
}
