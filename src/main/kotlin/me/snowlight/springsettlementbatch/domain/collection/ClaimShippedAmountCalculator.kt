package me.snowlight.springsettlementbatch.domain.collection

import me.snowlight.springsettlementbatch.domain.entity.claim.ClaimItem
import me.snowlight.springsettlementbatch.domain.enums.ClaimType
import me.snowlight.springsettlementbatch.domain.enums.ExtraFeePayer
import java.math.BigDecimal

class ClaimShippedAmountCalculator(
    private val item: ClaimItem
) {
    private val SHIPPING_AMOUNT: BigDecimal = BigDecimal.valueOf(3000L)

    fun getClaimShippedAmount(): BigDecimal {
        val claimReceipt = item.claimReceipt
        val shippingCount = getShippingCount(claimReceipt.requestType, claimReceipt.extraFeePayer)

        return SHIPPING_AMOUNT.multiply(shippingCount.toBigDecimal())
    }

    private fun getShippingCount(requestType: ClaimType, extraFeePayer: ExtraFeePayer): Int {
        if (extraFeePayer == ExtraFeePayer.BUYER) {
            return 0
        }

        return when(requestType) {
            ClaimType.CANCELED -> 0
            ClaimType.EXCHANGE -> -2
            ClaimType.RETURN -> -1
        }
    }
}
