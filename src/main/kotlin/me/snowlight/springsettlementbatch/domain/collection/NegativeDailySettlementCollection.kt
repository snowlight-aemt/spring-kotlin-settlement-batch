package me.snowlight.springsettlementbatch.domain.collection

import me.snowlight.springsettlementbatch.domain.entity.claim.ClaimItem
import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import java.time.LocalDate

class NegativeDailySettlementCollection(private val item: ClaimItem) {
    fun getSettlementDaily(): SettlementDaily {
        val orderItem = item.orderItem
        val orderItemSnapshot = orderItem.orderItemSnapshot
        val seller = orderItemSnapshot.seller

        val count = item.claimCount ?: -1
        val countToBigDecimal = count.toBigDecimal()

        val taxCalculator = TaxCalculator(orderItemSnapshot)
        val taxAmount = taxCalculator.getTaxAmount().multiply(countToBigDecimal)

        val orderItemSnapshot1 = PgSalesAmountMaterial(
            sellPrice = orderItemSnapshot.sellPrice,
            promotionAmount = orderItemSnapshot.promotionAmount,
            mileageUsageAmount = orderItemSnapshot.mileageUsageAmount,
        )
        val pgSalesAmount = PgSalesAmountCalculator(orderItemSnapshot1)
                                .getPgSaleAmount().multiply(countToBigDecimal)
        val commissionAmount = CommissionAmountCalculator(orderItemSnapshot)
                                    .getCommissionAmount().multiply(countToBigDecimal)

        val claimShippingFeeAmount = ClaimShippedAmountCalculator(item).getClaimShippedAmount()

        return SettlementDaily(
            settlementDate = LocalDate.now(),
            orderNo = orderItem.orderNo,
            orderCount = count,
            orderItemNo = orderItem.id,
            sellerNo = seller.id,
            sellerBusinessNumber = seller.businessNo,
            sellerName = seller.sellerName,
            sellType = seller.sellType,
            taxType = orderItemSnapshot.taxType,
            taxAmount = taxAmount,
            commissionAmount = commissionAmount,
            pgSalesAmount = pgSalesAmount,
            couponDiscountAmount = orderItemSnapshot.promotionAmount,
            mileageUsageAmount = orderItemSnapshot.mileageUsageAmount,
            shippingFeeAmount = orderItemSnapshot.defaultDeliveryAmount,
            claimReceiptNo = item.claimReceipt.id,
            claimShippingFeeAmount = claimShippingFeeAmount,
        )
    }
}