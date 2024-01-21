package me.snowlight.springsettlementbatch.core.job.total

import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementTotal
import org.springframework.batch.item.ItemProcessor
import java.time.LocalDate

class TotalSettlementItemProcessor
    : ItemProcessor<SummingSettlementResponse, SettlementTotal> {

    override fun process(item: SummingSettlementResponse): SettlementTotal {
        return SettlementTotal(
            settlementDate = LocalDate.now(),
            sellerNo = item.sellerNo,
            sellerName = item.sellerName,
            sellerBusinessNumber = item.sellerBusinessNumber,
            tasType = item.taxType,
            sellType = item.sellType,
            pgSalesAmount = item.sumPgSalesAmount,
            couponDiscountAmount = item.sumCouponDiscountAmount,
            mileageUsageAmount = item.sumMileageUsageAmount,
            shippingFeeAmount = item.sumShippingFeeAmount,
            claimShippingFeeAmount = item.sumClaimShippingFeeAmount,
            commissionAmount = item.sumCommissionAmount,
            taxAmount = item.sumTaxAmount
        )
    }
}