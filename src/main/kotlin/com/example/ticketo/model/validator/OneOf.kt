package com.example.ticketo.model.validator

import com.example.ticketo.model.validator.impl.OneOf
import javax.annotation.security.DenyAll
import javax.validation.Constraint
import kotlin.reflect.KClass


@MustBeDocumented
@Constraint(validatedBy = [OneOf::class])
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD, AnnotationTarget.ANNOTATION_CLASS,
        AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
annotation class OneOf(
        val enum : KClass<out Enum<*>>,
        val message: String = "",
        val groups: Array<KClass<out Any>> = [],
        val payload: Array<KClass<out Any>> = []
)
