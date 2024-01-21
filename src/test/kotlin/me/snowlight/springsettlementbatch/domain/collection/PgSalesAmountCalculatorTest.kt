package me.snowlight.springsettlementbatch.domain.collection

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import java.math.BigDecimal

@DisplayName("Pg 금액을 계산하는 계산기를 검증하는 테스트")
class PgSalesAmountCalculatorTest {
    private val samplePgSalesAmountMaterial = PgSalesAmountMaterial(
        sellPrice = BigDecimal.valueOf(10000.000),
        promotionAmount = BigDecimal.valueOf(0.000),
        mileageUsageAmount = BigDecimal.ZERO
    )

    @DisplayName("프로모션(쿠폰)도 없고, 적랍금 사용도 없는 경우")
    @Test
    fun test1() {
        val calculator = PgSalesAmountCalculator(samplePgSalesAmountMaterial)
        val pgSaleAmount = calculator.getPgSaleAmount()

        assertEquals(pgSaleAmount, BigDecimal.valueOf(10000.000))
    }

    @DisplayName("프로모션(쿠폰) 없고, 적랍금 사용도 있는 경우")
    @Test
    fun test2() {
        val pgSalesAmountMaterial = samplePgSalesAmountMaterial.copy(
            mileageUsageAmount = BigDecimal.valueOf(5000.100)
        )
        val calculator = PgSalesAmountCalculator(pgSalesAmountMaterial)
        val pgSaleAmount = calculator.getPgSaleAmount()

        assertEquals(pgSaleAmount, BigDecimal.valueOf(4999.900))
    }

    @DisplayName("프로모션(쿠폰) 있는 경우, 적랍금 사용도 있는 경우")
    @Test
    fun test3() {
        val pgSalesAmountMaterial = samplePgSalesAmountMaterial.copy(
            promotionAmount = BigDecimal.valueOf(1499.500),
            mileageUsageAmount = BigDecimal.valueOf(5000.100)
        )
        val calculator = PgSalesAmountCalculator(pgSalesAmountMaterial)
        val pgSaleAmount = calculator.getPgSaleAmount()

        assertEquals(pgSaleAmount, BigDecimal.valueOf(3500.400))
    }
}