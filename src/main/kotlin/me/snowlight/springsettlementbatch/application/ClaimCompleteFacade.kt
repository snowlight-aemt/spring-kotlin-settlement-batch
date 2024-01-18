package me.snowlight.springsettlementbatch.application

import me.snowlight.springsettlementbatch.domain.ClaimCompleteService
import org.springframework.stereotype.Service

@Service
class ClaimCompleteFacade(
    private val claimCompleteService: ClaimCompleteService,
) {
    fun complete(claimNo: Long) {
        claimCompleteService.complete(claimNo)
    }
}
