package me.snowlight.springsettlementbatch.core.job.purchaseconfired.claim

import me.snowlight.springsettlementbatch.domain.collection.CommissionAmountCalculator
import me.snowlight.springsettlementbatch.domain.collection.PgSalesAmountCalculator
import me.snowlight.springsettlementbatch.domain.collection.TaxCalculator
import me.snowlight.springsettlementbatch.domain.entity.claim.ClaimItem
import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor
import java.math.BigDecimal
import java.time.LocalDate

class ClaimDailySettlementProcessor: ItemProcessor<ClaimItem, SettlementDaily> {
    override fun process(item: ClaimItem): SettlementDaily {
        val orderItem = item.orderItem
        val orderItemSnapshot = orderItem.orderItemSnapshot
        val seller = orderItemSnapshot.seller

        val count = item.claimCount ?: -1
        val countToBigDecimal = count.toBigDecimal()

        val taxCalculator = TaxCalculator(orderItemSnapshot)
        val taxAmount = taxCalculator.getTaxAmount().multiply(countToBigDecimal)

        val pgSalesAmount = PgSalesAmountCalculator(orderItemSnapshot)
                            .getPgSaleAmount().multiply(countToBigDecimal)
        val commissionAmount = CommissionAmountCalculator(orderItemSnapshot)
                                .getCommissionAmount().multiply(countToBigDecimal)

        val claimShippingFeeAmount = BigDecimal.ZERO

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