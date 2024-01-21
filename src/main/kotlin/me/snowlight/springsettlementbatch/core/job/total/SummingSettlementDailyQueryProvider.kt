package me.snowlight.springsettlementbatch.core.job.total

import jakarta.persistence.Query
import me.snowlight.springsettlementbatch.domain.entity.settlement.SettlementDaily
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider
import java.time.LocalDate

class SummingSettlementDailyQueryProvider(
    // TODO 의미적으로 이슈가 있을 수 있다고 생각한다. (between a and b)
    private val startDate: LocalDate,   // 이번 달 첫 날 (23/10/01)
    private val endDate: LocalDate,     // 다음 달 첫 날 (23/11/01)
): AbstractJpaQueryProvider() {
    override fun createQuery(): Query {
        println("SummingSettlementDailyQueryProvider 파라미터 값 확인용")
        println(startDate)
        println(endDate)

        val createQuery = this.entityManager.createQuery(
            """
                select sd.sellerNo, sd.sellerNo, sd.sellerBusinessNumber, sd.taxType, sd.sellType,
                    sum(sd.pgSalesAmount) as sumPgSalesAmount,
                    sum(sd.couponDiscountAmount) as sumCouponDiscountAmount,
                    sum(sd.mileageUsageAmount) as sumMileageUsageAmount,
                    sum(sd.shippingFeeAmount) as sumShippingFeeAmount,
                    sum(sd.claimShippingFeeAmount) as sumClaimShippingFeeAmount,
                    sum(sd.commissionAmount) as sumCommissionAmount
                  from SettlementDaily sd
                 where sd.settlementDate between :startDate and :endDate
                 group by sd.sellerNo
            """.trimIndent(),
            SettlementDaily::class.java
        )

        createQuery.setParameter("startDate", startDate)
        createQuery.setParameter("endDate", endDate)

        return createQuery
    }

    override fun afterPropertiesSet() {
        TODO("Not yet implemented")
    }
}