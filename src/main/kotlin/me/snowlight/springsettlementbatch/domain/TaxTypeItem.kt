package me.snowlight.springsettlementbatch.domain

import java.math.BigDecimal

/**
 * TODO 왜 해당 클래스가 필요하지 확인이 필요함.
 */
sealed class TaxTypeItem {
    data class TaxItem(val price: BigDecimal ?= BigDecimal.ZERO): TaxTypeItem()
    data class ZeroTaxItem(val price: BigDecimal ?= BigDecimal.ZERO): TaxTypeItem()
    data class TaxFreeItem(val price: BigDecimal ?= BigDecimal.ZERO): TaxTypeItem()
}
