package me.snowlight.springsettlementbatch.infrastructure.database

import me.snowlight.springsettlementbatch.domain.ClaimCompleteExecutor
import me.snowlight.springsettlementbatch.domain.enums.ClaimStatus
import me.snowlight.springsettlementbatch.infrastructure.database.repository.ClaimReceiptRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class ClaimCompleteJpaExecutor(
    private val claimCompleteRepository: ClaimReceiptRepository,
) : ClaimCompleteExecutor {
    override fun updateCompletedAt(claimNo: Long) {
        val claimReceipt = claimCompleteRepository.findByIdOrNull(claimNo)
            ?: throw IllegalArgumentException("잘못된 클레임 ID 를 사용 중입니다.")

        val updateClaimReceipt = claimReceipt.copy(
            id = claimNo,
            completedAt = ZonedDateTime.now(),
            claimStatus = ClaimStatus.COMPLETED
        )

        claimCompleteRepository.save(updateClaimReceipt)
    }
}