package me.snowlight.springsettlementbatch.infrastructure.message

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class ClaimCompleteMessage @JsonCreator constructor(
    @JsonProperty("claimNo") val claimNo: Long,
    @JsonProperty("status") val status: String
)
