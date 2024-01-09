package me.snowlight.springsettlementbatch.core.job.purchaseconfired.delivery

import jakarta.persistence.Query
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider
import java.time.ZonedDateTime

class DeliveryConfirmedJpaQueryProvider(
    private val startDateTime: ZonedDateTime,
    private val endDateTime: ZonedDateTime,
): AbstractJpaQueryProvider() {
    override fun createQuery(): Query {
        val query = this.entityManager.createQuery(
            """
                SELECT oi 
                  FROM OrderItem oi 
                 WHERE oi.shippedCompleteAt BETWEEN :startDateTime AND :endDateTime
            """,
            OrderItem::class.java
        )

        query.setParameter("startDateTime", this.startDateTime)
        query.setParameter("endDateTime", this.endDateTime)

        return query
    }

    override fun afterPropertiesSet() {
        TODO("Not yet implemented")
    }
}