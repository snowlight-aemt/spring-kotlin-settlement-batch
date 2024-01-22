package me.snowlight.springsettlementbatch.application

import me.snowlight.springsettlementbatch.domain.PurchaseConfirmedService
import org.springframework.stereotype.Component

@Component
class PurchaseConfirmedFacade(
    private val purchaseConfirmedService: PurchaseConfirmedService
) {
    fun orderComplete(orderNo: Long) {
        purchaseConfirmedService.complete(orderNo);
    }

}
