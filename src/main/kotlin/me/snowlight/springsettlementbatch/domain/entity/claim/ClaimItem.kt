package me.snowlight.springsettlementbatch.domain.entity.claim

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import me.snowlight.springsettlementbatch.domain.entity.order.OrderItem
import java.time.ZonedDateTime

@Entity
@Table(name = "claim_item")
data class ClaimItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_item_no")
    val id: Long = 0L,
//    val claimReceiptNo: Long,
    @ManyToOne
    @JoinColumn(name = " claim_receipt_no", insertable = false, updatable = false)
    val claimReceipt: ClaimReceipt,

    @Column(nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @Column(nullable = false)
    val updatedAt: ZonedDateTime = ZonedDateTime.now(),
    val deletedAt: ZonedDateTime?,

//    val orderItemNo: Long,
    @OneToOne
    @JoinColumn(name = "order_item_no", insertable = false, updatable = false)
    val orderItem: OrderItem,

    @Column(nullable = false)
    val claimCount: Int? = -1,
)

/*
create table claim_item
(
    claim_item_no    bigint                             not null
        primary key,
    claim_receipt_no bigint                             not null,
    created_at       datetime default CURRENT_TIMESTAMP not null,
    updated_at       datetime default CURRENT_TIMESTAMP not null,
    deleted_at       datetime                           null,
    order_item_no    bigint                             not null,
    claim_count      int                                null
);

 */
