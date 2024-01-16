package me.snowlight.springsettlementbatch.core.job.purchaseconfired.daily

import me.snowlight.springsettlementbatch.domain.collection.CommissionAmountCalculator
import me.snowlight.springsettlementbatch.domain.collection.PgSalesAmountCalculator
import me.snowlight.springsettlementbatch.domain.collection.TaxCalculator
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.ItemProcessor
import java.math.BigDecimal
import java.time.LocalDate

class DailySettlementProcessor: ItemProcessor<OrderItem, SettlementDaily> {
    override fun process(orderItem: OrderItem): SettlementDaily {
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
            sellerNo = orderItemSnapshot.sellerNo,
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