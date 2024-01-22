package me.snowlight.springsettlementbatch.infrastructure.message

import me.snowlight.springsettlementbatch.domain.ClaimCompleteExecutor
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ClaimCompleteProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) : ClaimCompleteExecutor {
    private val topicName = "claimComplete"

    override fun updateCompletedAt(claimNo: Long) {
        val claimCompleteMessage = ClaimCompleteMessage(claimNo, "COMPLETE")
        kafkaTemplate.send(topicName, claimNo.toString(), claimCompleteMessage)
    }
}