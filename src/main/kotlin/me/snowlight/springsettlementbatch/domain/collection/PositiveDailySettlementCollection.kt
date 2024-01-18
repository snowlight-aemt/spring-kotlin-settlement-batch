package me.snowlight.springsettlementbatch.domain.collection

import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import java.math.BigDecimal
import java.time.LocalDate

class PositiveDailySettlementCollection(private val orderItem: OrderItem) {
    fun getSettlementDaily(): SettlementDaily {
        val orderItemSnapshot = orderItem.orderItemSnapshot
        val seller = orderItemSnapshot.seller
        val count = orderItem.orderCount
        val countToBigDecimal = count.toBigDecimal()

        // 세금 계산
        val taxCalculator = TaxCalculator(orderItemSnapshot)
        val taxAmount: BigDecimal = taxCalculator.getTaxAmount().multiply(countToBigDecimal)

        // 정산금액에 필요한 데이터 만들기
        val pgSalesAmountCalculator = PgSalesAmountCalculator(orderItemSnapshot)
        val pgSalesAmount: BigDecimal = pgSalesAmountCalculator.getPgSaleAmount().multiply(countToBigDecimal)
        val commissionAmountCalculator = CommissionAmountCalculator(orderItemSnapshot)
        val commissionAmount: BigDecimal = commissionAmountCalculator.getCommissionAmount().multiply(countToBigDecimal)

        return SettlementDaily(
            settlementDate = LocalDate.now(),
            orderNo = orderItem.orderNo,
            orderCount = count,
            orderItemNo = orderItem.id,
            sellerNo = seller.id,
            sellerBusinessNo = seller.businessNo,
            sellerName = seller.sellerName,
            sellType = seller.sellType,
            taxType = orderItemSnapshot.taxType,
            taxAmount = taxAmount,
            commissionAmount = commissionAmount,
            pgSalesAmount = pgSalesAmount,
        )
    }
}