package me.snowlight.springsettlementbatch.domain.enums

enum class ClaimType(val value: String) {
    CANCELED("CANCELED"),   // 주문 취소
    EXCHANGE("EXCHANGE"),   // 교환
    RETURN("RETURN"),       // 반품
}