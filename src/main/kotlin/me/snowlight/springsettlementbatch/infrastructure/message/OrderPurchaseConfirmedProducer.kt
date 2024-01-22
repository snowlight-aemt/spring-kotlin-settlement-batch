package me.snowlight.springsettlementbatch.infrastructure.message

import me.snowlight.springsettlementbatch.domain.OrderPurchaseConfirmedExecutor
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OrderPurchaseConfirmedProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) : OrderPurchaseConfirmedExecutor {
    private val topicName = "purchaseConfirmed"

    override fun confirmed(orderNo: Long) {
        kafkaTemplate.send(this.topicName, orderNo.toString(), orderNo)
    }
}