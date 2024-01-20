package me.snowlight.springsettlementbatch.domain.enums

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class ExtraFeePayerConverter: AttributeConverter<ExtraFeePayer, Int> {
    override fun convertToDatabaseColumn(attribute: ExtraFeePayer?): Int {
        return attribute?.value ?: throw IllegalArgumentException("Invalid ExtraFeePayer value")
    }

    override fun convertToEntityAttribute(dbData: Int?): ExtraFeePayer {
        return when(dbData) {
            0 -> ExtraFeePayer.BUYER
            1 -> ExtraFeePayer.SELLER
            2 -> ExtraFeePayer.PLATFORM
            else -> throw IllegalArgumentException("Invalid ExtraFeePayer value $dbData")
        }
    }
}