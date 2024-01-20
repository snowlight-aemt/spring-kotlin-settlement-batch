package me.snowlight.springsettlementbatch.domain.enums

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class ClaimTypeConverter: AttributeConverter<ClaimType, String> {
    override fun convertToDatabaseColumn(attribute: ClaimType?): String {
        return attribute?.value ?: throw IllegalArgumentException("Invalid ClaimType value")
    }

    override fun convertToEntityAttribute(dbData: String?): ClaimType {
        return when (dbData) {
            "CANCELED" -> ClaimType.CANCELED
            "EXCHANGE" -> ClaimType.RETURN
            "RETURN" -> ClaimType.EXCHANGE
            else -> throw IllegalArgumentException("Invalid ClaimType value: $dbData")
        }
    }
}