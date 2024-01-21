package me.snowlight.springsettlementbatch.application.dummy

import me.snowlight.springsettlementbatch.domain.entity.Product
import me.snowlight.springsettlementbatch.domain.entity.Seller
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItemSnapshot
import me.snowlight.springsettlementbatch.infrastructure.database.repository.OrderItemRepository
import me.snowlight.springsettlementbatch.infrastructure.database.repository.OrderItemSnapshotRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.ZonedDateTime

@Service
class DummyFacade(
    private val orderItemRepository: OrderItemRepository,
    private val orderItemSnapshotRepository: OrderItemSnapshotRepository,
) {

    fun createOrder(orderNo: Long) {
        val orderItemSnapshot = OrderItemSnapshot(
//            productNo = 1,
//            sellerNo = 1,
            sellPrice = BigDecimal.valueOf(10000.000),
            supplyPrice = BigDecimal.valueOf(1000.000),
            promotionAmount = BigDecimal.valueOf(2000.000),
            mileageUsageAmount = BigDecimal.valueOf(2000.000),
            seller = Seller(1, sellerName = "가나다", sellType = "P"),
            product = Product(1, productName = "테스트", sellerNo = 1, category = 1)
        )
        orderItemSnapshotRepository.save(orderItemSnapshot)

        val orderItem = OrderItem(
            orderNo = orderNo,
            orderCount = 1,
//            orderItemSnapshotNo = orderNo,
            itemDeliveryStatus = 0,
            shippedCompleteAt = ZonedDateTime.now(),
            orderItemSnapshot = orderItemSnapshot,
        )

        orderItemRepository.save(orderItem)
    }

    fun createClaim() {

    }
}