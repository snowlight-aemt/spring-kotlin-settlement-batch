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
@Table(name = "settlement_daily")
data class SettlementDaily (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settlement_no")
    val id: Long = 0L,
    val settlementDate: LocalDate,
    @Column(nullable = false)
    val orderNo: Long,
    @Column(nullable = false)
    val orderItemNo: Long,
    @Column(nullable = false)
    val orderCount: Int = 1,
    val claimReceiptNo: Long? = null,
    val sellerNo: Long,

    val sellerName: String,
    val sellerBusinessNo: Int?,
    @Comment("세금 유형 : 관세, 면세, 영세, ")
    @Column(length = 4, nullable = false)
    val taxType: String = "TAX",  // TODO ENUM
    @Comment("판매 유형 : 위탁(CONSIGNMENT), 매입(PURCHASE)")
    @Column(nullable = false)
    val sellType: String = "CONSIGNMENT", // TODO ENUM
    @Comment("PG 판매 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val pgSalesAmount: BigDecimal = BigDecimal.ZERO,
    @Comment("쿠폰 할인 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val couponDiscountAmount: BigDecimal = BigDecimal.ZERO,
    @Comment("마일리지 사용 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val mileageUsageAmount: BigDecimal = BigDecimal.ZERO,
    @Comment("발생 배송비 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val shippingFeeAmount: BigDecimal = BigDecimal.ZERO,
    @Comment("발생 클레임 배송비 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val claimShippingFeeAmount: BigDecimal? = BigDecimal.ZERO,
    @Comment("수수료 금액")
    @Column(precision = 14, scale = 5, nullable = false)
    val commissionAmount: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false)
    val taxAmount: BigDecimal? = BigDecimal.ZERO,
    val createdBy: ZonedDateTime = ZonedDateTime.now(),
    val updatedBy: ZonedDateTime = ZonedDateTime.now(),
    val deletedBy: ZonedDateTime? = null,
)

/*

create table Settlement_total
(
    settlement_total_id       bigint auto_increment
        primary key,
    settlement_date           date                                     not null,
    seller_no                 bigint                                   not null,
    seller_name               varchar(255)                             not null,
    seller_business_number    varchar(255)                             null,
    tax_type                  varchar(255)   default 'TAX'             null,
    sell_type                 varchar(255)   default 'CONSIGNMENT'     null,
    pg_sales_amount           decimal(14, 5) default 0.00000           not null,
    coupon_discount_amount    decimal(14, 5) default 0.00000           not null,
    mileage_usage_amount      decimal(14, 5) default 0.00000           not null,
    shipping_fee_amount       decimal(14, 5) default 0.00000           not null,
    claim_shipping_fee_amount decimal(14, 5) default 0.00000           not null,
    commission_amount         decimal(14, 5) default 0.00000           not null,
    created_at                datetime       default CURRENT_TIMESTAMP null,
    updated_at                datetime       default CURRENT_TIMESTAMP null,
    deleted_at                datetime                                 null
);

 */