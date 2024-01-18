package me.snowlight.springsettlementbatch.domain

interface ClaimCompleteExecutor {
    fun updateCompletedAt(claimNo: Long)

}
