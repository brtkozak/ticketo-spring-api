package com.example.ticketo.controller.request_dto

import com.example.ticketo.model.validator.OneOf
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class OpinionRequest(
        @field:NotNull(message = "User can not be empty")
        val toUser : Int,
        @field:NotBlank (message = "Value must be one of [positive; negative]")
        @field:OneOf(enum = OpinionValue::class, message = "Value must be one of [positive, negative]")
        val value : String
)

enum class OpinionValue {
        POSITIVE, NEGATIVE
}