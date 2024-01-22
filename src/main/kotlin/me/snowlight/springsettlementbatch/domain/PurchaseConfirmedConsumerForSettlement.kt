package me.snowlight.springsettlementbatch.domain

import me.snowlight.springsettlementbatch.domain.collection.PositiveDailySettlementCollection
import me.snowlight.springsettlementbatch.infrastructure.database.repository.OrderItemRepository
import me.snowlight.springsettlementbatch.infrastructure.database.repository.SettlementDailyRepository
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PurchaseConfirmedConsumerForSettlement(
    private val orderItemRepository: OrderItemRepository,
    private val settlementDailyRepository: SettlementDailyRepository,
) {
    @KafkaListener(topics = ["purchaseConfirmed"], groupId = "order-complete-consumer-group")
    fun listen(message: String) {
        try {
            processMessage(message);
        } catch (e: Exception) {
            handleException(e)
        }
    }
    private fun processMessage(message: String) {
        val orderItemList = orderItemRepository.findByOrderNo(message.toLong())
        val settlementDailyList = orderItemList.map {
            // TODO 재 활용된다. 순수 자바로 ?
            PositiveDailySettlementCollection(it).getSettlementDaily()
        }
        settlementDailyRepository.saveAll(settlementDailyList)
    }
    private fun handleException(e: Exception) {
        TODO("Not yet implemented")
    }
}