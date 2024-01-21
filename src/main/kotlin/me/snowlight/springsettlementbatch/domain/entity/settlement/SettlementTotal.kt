package me.snowlight.springsettlementbatch.domain.entity.settlement

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.math.BigDecimal
import java.time.LocalDate
import java.time.ZonedDateTime

@Entity
@Table(name = "settlement_total")
data class SettlementTotal (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settlement_total_no")
    val id: Long = 0L,
    val settlementDate: LocalDate,
    val sellerNo: Long,

    val sellerName: String,
    val sellerBusinessNumber: String?,
    @Comment("세금 유형 : 관세, 면세, 영세, ")
    @Column(length = 4, nullable = false)
    val tasType: String = "TAX",  // TODO ENUM
    @Comment("판매 유형 : 위탁(CONSIGNMENT), 메인(PURCHASE)")
    @Column(nullable = false)
    val sellType: String = "CONSIGNMENT", // TODO ENUM
    @Comment("PG 판매 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val pgSalesAmount: BigDecimal,
    @Comment("쿠폰 할인 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val couponDiscountAmount: BigDecimal,
    @Comment("마일리지 사용 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val mileageUsageAmount: BigDecimal,
    @Comment("발생 배송비 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val shippingFeeAmount: BigDecimal,
    @Comment("발생 클레임 배송비 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val claimShippingFeeAmount: BigDecimal,
    @Comment("수수료 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val commissionAmount: BigDecimal,
    @Comment("세금 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val taxAmount: BigDecimal,
    val createdBy: ZonedDateTime = ZonedDateTime.now(),
    val updatedBy: ZonedDateTime = ZonedDateTime.now(),
    val deletedBy: ZonedDateTime? = null,
)