package com.example.ticketo.controller.request_dto

import com.example.ticketo.model.validator.OneOf
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class OfferRequest(
        @field:NotNull(message = "Event id can not be empty")
        val event : Int,
        @field:NotBlank(message = "Value must be one of [buy; sell]")
        @field:OneOf(enum = OfferValue::class, message = "Value must be one of [buy; sell]")
        val value : String
)

enum class OfferValue {
    BUY, SELL
}