package me.snowlight.springsettlementbatch.core.job.total

import java.math.BigDecimal

data class SummingSettlementResponse(
    val sellerNo: Long,
    val sellerName: String,
    val sellerBusinessNumber: String?,
    val taxType: String,
    val sellType: String,
    val sumPgSalesAmount: BigDecimal,           // 순수 금액
    val sumCouponDiscountAmount: BigDecimal,
    val sumMileageUsageAmount: BigDecimal,
    val sumShippingFeeAmount: BigDecimal,
    val sumClaimShippingFeeAmount: BigDecimal,
    val sumCommissionAmount: BigDecimal,
    val sumTaxAmount: BigDecimal,
)
