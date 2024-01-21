package me.snowlight.springsettlementbatch.infrastructure.database.repository

import me.snowlight.springsettlementbatch.domain.entity.order.OrderItemSnapshot
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemSnapshotRepository : JpaRepository<OrderItemSnapshot, Long>