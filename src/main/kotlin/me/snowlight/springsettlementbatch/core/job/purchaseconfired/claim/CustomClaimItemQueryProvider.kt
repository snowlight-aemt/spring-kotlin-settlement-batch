package me.snowlight.springsettlementbatch.core.job.purchaseconfired.claim

import jakarta.persistence.Query
import me.snowlight.springsettlementbatch.domain.entity.claim.ClaimItem
import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider

class CustomClaimItemQueryProvider: AbstractJpaQueryProvider() {
    override fun createQuery(): Query {
        val createQuery = this.entityManager.createQuery(
            """
               SELECT ci
                 FROM ClaimItem ci
                 LEFT JOIN SettlementDaily sd ON ci.claimReceipt.id = sd.claimReceiptNo
                 JOIN ClaimReceipt cr ON ci.claimReceipt.id = cr.id
                WHERE sd.id IS NULL 
                  AND cr.completedAt IS NOT NULL 
            """,
            ClaimItem::class.java
        )

        return createQuery
    }

    override fun afterPropertiesSet() {
        TODO("Not yet implemented")
    }

}