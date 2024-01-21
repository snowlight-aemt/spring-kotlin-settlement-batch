package me.snowlight.springsettlementbatch.domain.collection

import java.math.BigDecimal

data class PgSalesAmountMaterial(
    val sellPrice: BigDecimal? = BigDecimal.ZERO,
    val promotionAmount: BigDecimal? = BigDecimal.ZERO,
    val mileageUsageAmount: BigDecimal? = BigDecimal.ZERO,
)