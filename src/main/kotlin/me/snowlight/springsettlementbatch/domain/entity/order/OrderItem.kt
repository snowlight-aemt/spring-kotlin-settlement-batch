package me.snowlight.springsettlementbatch.domain.entity.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.ZonedDateTime

@Entity
@Table(name = "order_item")
data class OrderItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_no")
    val id: Long,
    @Comment("주문원장")
    @Column(nullable = false)
    val orderNo: Long,
    @Comment("주문 상품 스넵샵")
    @Column(nullable = false)
    val orderItemSnapshotNo: Long,
    @OneToOne
    @JoinColumn(name = "order_item_snapshot_no", referencedColumnName = "id", insertable = false, updatable = false)
    val orderItemSnapshot: OrderItemSnapshot,
    @Comment("주문 수량")
    @Column(nullable = false)
    val orderCount: Int = 1,
    @Comment("배송 상태")
    val itemDeliveryStatus: Int = 0, // Enum :
    @Comment("구매확장 날짜")
    val purchaseConfirmedAt: ZonedDateTime? = null,
    @Comment("배송완료 날짜")
    val shippedCompleteAt: ZonedDateTime,
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
)