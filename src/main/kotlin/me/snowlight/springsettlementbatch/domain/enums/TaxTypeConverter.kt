package me.snowlight.springsettlementbatch.domain.enums

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class TaxTypeConverter: AttributeConverter<TaxType, String> {
    override fun convertToDatabaseColumn(attribute: TaxType?): String {
        return attribute?.value ?: throw IllegalArgumentException("Invalid TaxType value")
    }

    override fun convertToEntityAttribute(dbData: String?): TaxType {
        return when(dbData) {
            "TAX" -> TaxType.TAX
            "ZERO" -> TaxType.ZERO
            "FREE" -> TaxType.FREE
            else -> throw IllegalArgumentException("Invalid ClaimStatus value $dbData")
        }
    }
}