package me.snowlight.springsettlementbatch.domain.entity.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.math.BigDecimal

@Entity
@Table(name = "order_item_snapshot")
data class OrderItemSnapshot(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_snapshot")
    val id: Long = 0,
    val productNo: Long,
    val sellerNo: Long,
    @Comment("판매가")
    @Column(precision = 14, scale = 5, nullable = false)
    val sellPrice: BigDecimal = BigDecimal.ZERO,
    @Comment("공급가")
    @Column(precision = 14, scale = 5, nullable = false)
    val supplyPrice: BigDecimal = BigDecimal.ZERO,
    @Comment("할인 가격")
    @Column(precision = 14, scale = 5, nullable = false)
    val promotionAmount: BigDecimal = BigDecimal.ZERO,
    @Comment("배송비")
    @Column(precision = 14, scale = 5, nullable = false)
    val defaultDeliveryAmount: BigDecimal = BigDecimal.valueOf(3000),
    @Comment("세금유형")
    @Column(length = 4, nullable = false)
    val taxType: String = "TAX", // TODO ENUM
    @Comment("세금")
    @Column(nullable = false)
    val taxRate: Int = 3,
    @Comment("상품 유형")
    @Column(nullable = false)
    val itemCategory: Int = 0, // TODO ENUM
)