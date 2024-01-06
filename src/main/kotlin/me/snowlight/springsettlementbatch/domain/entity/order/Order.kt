package me.snowlight.springsettlementbatch.domain.entity.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.Comment
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
@Table(name = "order_origin")
@Comment("주문 테이블")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no")
    val id: Long = 0L,
    @Comment("생성 날짜")
    @Column(nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Comment("수정 날짜")
    @Column(nullable = false)
    val updatedAt: ZonedDateTime = ZonedDateTime.now(),
    @Comment("삭제 날짜")
    val deletedAt: ZonedDateTime? = null,
    @Comment("결제 완료 날짜")
    val paidConfirmedAt: ZonedDateTime? = null,
    @Comment("결제 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val paidPgAmount: BigDecimal = BigDecimal.ZERO,
    @Comment("쿠폰 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val couponDiscountAmount: BigDecimal = BigDecimal.ZERO,
    @Comment("마일리지 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val usedMileageAmount: BigDecimal = BigDecimal.ZERO,
)
