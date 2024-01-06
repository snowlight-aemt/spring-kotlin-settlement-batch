package me.snowlight.springsettlementbatch.domain.entity.claim

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
@Table(name = "claim_refund_history")
data class ClaimRefundHistory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim-refund-no")
    val id: Long = 0L,
    val claimReceiptNo: Long,

    @Column(nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(nullable = false)
    val updatedAt: ZonedDateTime = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,

    @Comment("환불 날짜")
    val refundedAt: ZonedDateTime? = null,

    val sellerNo: Long,
    @Comment("현금형 금액")
    val refundCashAmount: BigDecimal = BigDecimal.ZERO,
    @Comment("쿠폰 금액")
    val couponSaleAmount: BigDecimal = BigDecimal.ZERO,
    @Comment("미일리지 금액")
    val refundMileageAmount: BigDecimal = BigDecimal.ZERO,
    @Comment("배송비 차감")
    val refundDeliveryAmount: BigDecimal = BigDecimal.ZERO,
    @Comment("배송비 발생")
    val subtractDeliveryAmount: BigDecimal = BigDecimal.ZERO,
)
