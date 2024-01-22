package me.snowlight.springsettlementbatch.domain

import com.fasterxml.jackson.databind.ObjectMapper
import me.snowlight.springsettlementbatch.domain.collection.NegativeDailySettlementCollection
import me.snowlight.springsettlementbatch.infrastructure.database.repository.ClaimItemRepository
import me.snowlight.springsettlementbatch.infrastructure.database.repository.SettlementDailyRepository
import me.snowlight.springsettlementbatch.infrastructure.message.ClaimCompleteMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ClaimCompleteConsumerForSettlement(
    private val claimItemRepository: ClaimItemRepository,
    private val settlementDailyRepository: SettlementDailyRepository,
) {
    @KafkaListener(topics = ["claimComplete"], groupId = "claim-consumer-group")
    fun listen(message: String) {
        try {
            processMessage(message);
        } catch (e: Exception) {
            handleException(e)
        }
    }

    // TODO 역 직렬화
    private fun processMessage(message: String) {
        val mapper = ObjectMapper()
        val deserializeMessage = mapper.readValue(message, ClaimCompleteMessage::class.java)
        val claimItemList = claimItemRepository.findByClaimReceipt_Id(deserializeMessage.claimNo)
        val negativeSettlementDailyList = claimItemList.map {
            // TODO 재 활용된다. 순수 자바로 ?
            NegativeDailySettlementCollection(it).getSettlementDaily()
        }
        settlementDailyRepository.saveAll(negativeSettlementDailyList)
    }
    private fun handleException(e: Exception) {
        TODO("Not yet implemented")
    }
}