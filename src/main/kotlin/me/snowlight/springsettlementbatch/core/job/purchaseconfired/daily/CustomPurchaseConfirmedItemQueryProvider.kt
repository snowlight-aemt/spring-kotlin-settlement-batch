package me.snowlight.springsettlementbatch.core.job.purchaseconfired.daily

import jakarta.persistence.Query
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider
import java.time.ZonedDateTime

class CustomPurchaseConfirmedItemQueryProvider(
    private val startDateTime: ZonedDateTime,
    private val endDateTime: ZonedDateTime,
): AbstractJpaQueryProvider() {
    override fun createQuery(): Query {
        val createQuery = this.entityManager.createQuery(
            """
                SELECT oi
                  FROM OrderItem oi
                 WHERE oi.purchaseConfirmedAt BETWEEN :startDateTime AND :endDateTime
            """,
            OrderItem::class.java
        )

        createQuery.setParameter("startDateTime", startDateTime)
        createQuery.setParameter("endDateTime", endDateTime)

        return createQuery
    }

    override fun afterPropertiesSet() {
        TODO("Not yet implemented")
    }

}