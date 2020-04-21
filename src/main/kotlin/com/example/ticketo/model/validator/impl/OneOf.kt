package com.example.ticketo.model.validator.impl

import com.example.ticketo.model.validator.OneOf
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext


class OneOf : ConstraintValidator<OneOf, CharSequence> {

    private var acceptedValues = mutableListOf<String>()

    override fun initialize(annotation: OneOf) {
        annotation.enum.java.enumConstants.forEach { acceptedValues.add(it.name.toLowerCase()) }
    }

    override fun isValid(value : CharSequence?, p1: ConstraintValidatorContext?): Boolean {
        if(value == null)
            return true;
        return acceptedValues.contains(value)
    }

}