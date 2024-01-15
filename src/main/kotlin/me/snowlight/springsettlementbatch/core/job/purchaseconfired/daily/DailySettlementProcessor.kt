package me.snowlight.springsettlementbatch.core.job.purchaseconfired.daily

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

        // 세금 계산
        val taxAmount: BigDecimal = BigDecimal.ZERO

        // 정산금액에 필요한 데이터 만들기
        val commissionAmount: BigDecimal = BigDecimal.ZERO
        val pgSalesAmount: BigDecimal = BigDecimal.ZERO
        
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