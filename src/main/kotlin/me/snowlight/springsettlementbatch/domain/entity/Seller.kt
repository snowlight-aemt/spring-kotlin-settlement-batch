package me.snowlight.springsettlementbatch.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.ZonedDateTime

@Entity
@Table(name = "seller")
data class Seller (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_no")
    val id: Long,
    val sellerName: String,
    val businessNo: Int? = 0,
    @Comment("판매유형 ( PURCHASE : 매입 , CONSIGNMENT : 위탁 )")
    val sellType: String,
    val bankType: String? = null,
    @Comment("계좌 번호")
    val accountNo: Int? = null,
    @Comment("계좌 주")
    val accountOwnerName: String? = null,
    val isActive: Boolean? = true,
    val defaultDeliveryAmount: Int? = 3000,
    @Comment("수수료")
    val commission: Int? = 0,

    val createdAt: ZonedDateTime? = ZonedDateTime.now(),
    val updatedAt: ZonedDateTime? = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,
)