package me.snowlight.springsettlementbatch.infrastructure.database.repository

import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import java.time.ZonedDateTime

interface OrderItemRepository: JpaRepository<OrderItem, Long> {
    fun findByShippedCompleteAtBetween(startDateTime: ZonedDateTime, endDateTime: ZonedDateTime): List<OrderItem>
    fun findByOrderNo(orderNo: Long): List<OrderItem>
}
