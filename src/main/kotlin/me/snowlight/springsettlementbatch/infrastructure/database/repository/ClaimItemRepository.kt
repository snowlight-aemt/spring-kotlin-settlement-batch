package me.snowlight.springsettlementbatch.infrastructure.database.repository

import me.snowlight.springsettlementbatch.domain.entity.claim.ClaimItem
import org.springframework.data.jpa.repository.JpaRepository

interface ClaimItemRepository : JpaRepository<ClaimItem, Long> {
    fun findByClaimReceipt_Id(claimNo: Long): List<ClaimItem>
}