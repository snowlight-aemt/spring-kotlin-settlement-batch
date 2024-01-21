package me.snowlight.springsettlementbatch.domain.enums

// TODO 숫자를 사용하는 이유가?
enum class ExtraFeePayer(val value: Int) {
    BUYER(0),       // 고객
    SELLER(1),      // 셀러
    PLATFORM(2),    // 플렛폼
}