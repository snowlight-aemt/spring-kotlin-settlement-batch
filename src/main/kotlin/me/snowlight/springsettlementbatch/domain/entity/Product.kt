package me.snowlight.springsettlementbatch.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedBy
import java.math.BigDecimal
import java.time.ZonedDateTime

@Entity
@Table(name = "product")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_no")
    val id: Long = 0L,
    val productName: String,
    val sellerNo: Long,

    @Comment("상품 카테고리")
    val category: Int,

    @Comment("세금유형")
    @Column(length = 4, nullable = false)
    val taxType: String = "TAX",
    @Comment("판매가")
    @Column(precision = 14, scale = 5, nullable = false)
    val sellPrice: BigDecimal = BigDecimal.ZERO,
    @Comment("공급가")
    @Column(precision = 14, scale = 5, nullable = false)
    val supplyPrice: BigDecimal = BigDecimal.ZERO,
    val isActive: Boolean = true,

    val createdBy: ZonedDateTime = ZonedDateTime.now(),
    val updatedBy: ZonedDateTime = ZonedDateTime.now(),
    val deletedBy: ZonedDateTime? = null,
)

/*
create table product
(
    product_no   bigint auto_increment comment '상품 식별키 '
        primary key,
    created_at   datetime       default CURRENT_TIMESTAMP not null comment '생성시간',
    updated_at   datetime       default CURRENT_TIMESTAMP not null comment '수정시간',
    deleted_at   datetime                                 null comment '삭제시간',
    product_name varchar(100)                             not null,
    seller_no    bigint                                   not null comment '셀러pk',
    category     int                                      not null comment '상품 카테고리',
    tax_type     char(4)        default 'TAX'             not null comment '세금유형',
    sell_price   decimal(14, 5) default 0.00000           not null comment '판매가 ',
    supply_price decimal(14, 5) default 0.00000           not null comment '공급가 ',
    is_active    tinyint(1)     default 1                 null comment '상품 활성화 여부 '
)
    comment '상품정보 테이블 ';
 */