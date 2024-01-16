package me.snowlight.springsettlementbatch.domain.collection

import me.snowlight.springsettlementbatch.domain.TaxTypeItem
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItemSnapshot
import me.snowlight.springsettlementbatch.domain.enums.TaxType
import org.springframework.scheduling.config.Task
import java.math.BigDecimal

class TaxCalculator(
    private val orderItemSnapshot: OrderItemSnapshot,
) {
    // TODO 다른 곳에서 가져올 수 도 있다.
    val TAX_RATE = 0.03

    fun getTaxAmount(): BigDecimal {
        val taxTypeItem = getTaxTypeItem()

        return when (taxTypeItem) {
            is TaxTypeItem.TaxItem -> getCalculatorTaxAmount(taxTypeItem)
            is TaxTypeItem.ZeroTaxItem -> taxTypeItem.price?: BigDecimal.ZERO
            is TaxTypeItem.TaxFreeItem -> taxTypeItem.price?: BigDecimal.ZERO
        }
    }

    private fun getTaxTypeItem(): TaxTypeItem {
        val taxType = orderItemSnapshot.taxType

        return when (taxType) {
            TaxType.TAX -> TaxTypeItem.TaxItem(orderItemSnapshot.sellPrice)
            TaxType.FREE -> TaxTypeItem.TaxFreeItem(BigDecimal.ZERO)
            TaxType.ZERO -> TaxTypeItem.ZeroTaxItem(BigDecimal.ZERO)
            null -> TaxTypeItem.TaxItem(BigDecimal.ZERO)
        }
    }

   private fun getCalculatorTaxAmount(taxItem: TaxTypeItem.TaxItem): BigDecimal {
        val price = taxItem.price
        val taxRate = BigDecimal.valueOf(TAX_RATE)

        // 상품 카테고리에 따라서 세율이 다른 경우 여기서 분기를 할 수 도 있지 않을까? (필요하다면 클래스 추출)

        return price!!.multiply(taxRate)
    }
}