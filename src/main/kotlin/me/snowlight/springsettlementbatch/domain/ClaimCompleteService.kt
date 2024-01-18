package me.snowlight.springsettlementbatch.domain

import org.springframework.stereotype.Service

@Service
class ClaimCompleteService(
    private val executor: ClaimCompleteExecutor,
) {
    fun complete(claimNo: Long) {
        executor.updateCompletedAt(claimNo)
    }
}
