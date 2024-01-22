package me.snowlight.springsettlementbatch.application

import me.snowlight.springsettlementbatch.domain.PurchaseConfirmedService
import me.snowlight.springsettlementbatch.infrastructure.database.repository.OrderItemRepository
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class PurchaseConfirmedFacade(
    private val purchaseConfirmedService: PurchaseConfirmedService,
    private val orderItemRepository: OrderItemRepository,
) {

    fun orderComplete(orderNo: Long) {
        // Transaction 필요
        /*
         * TODO 내 의견 : OrderItem 구매확정을 위한 작업을 하고, 정산 작업을 카프카로 넘긴다.
         *  따라서 트랜잭션이 분리되고 `보상 트랜잭션` 을 생각해야 한다.
         */
        val orderItemList = orderItemRepository.findByOrderNo(orderNo)
        val orderItemListPurchaseConfirmed = orderItemList.map {
            it.copy(purchaseConfirmedAt = ZonedDateTime.now())
        }
        orderItemRepository.saveAll(orderItemListPurchaseConfirmed)

        // Transaction 필요 없음.
        purchaseConfirmedService.complete(orderNo);
    }

}
