package me.snowlight.springsettlementbatch.domain.entity.claim

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import java.time.ZonedDateTime

@Entity
@Table(name = "claim_origin")
data class ClaimReceipt(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_receipt_no")
    val id: Long = 0L,
    val orderNo: Long,

    @Column(nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(nullable = false)
    val updatedAt: ZonedDateTime = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime? = null,

    val completedAt: ZonedDateTime? = null,

    @Comment("클레임 타입 : 환불, 교환, 취소 ...")
    @Column(nullable = false)
    val requestType: String, // TODO ENUM
    @Comment("클레임 상태 : 접수, 진행, 완료, 철회")
    @Column(nullable = false)
    val claimStatus: Int, // TODO ENUM
    @Comment("부담 주체 : 고객, 셀러, 플렛폼")
    @Column(nullable = false)
    val extraFeePayer: Int, // TODO ENUM
    @Comment("클레임 사유")
    @Column(nullable = false)
    val claimReason: Int // TODO ENUM
)