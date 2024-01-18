package me.snowlight.springsettlementbatch.infrastructure.database.repository

import me.snowlight.springsettlementbatch.domain.entity.claim.ClaimReceipt
import org.springframework.data.jpa.repository.JpaRepository

interface ClaimReceiptRepository: JpaRepository<ClaimReceipt, Long>