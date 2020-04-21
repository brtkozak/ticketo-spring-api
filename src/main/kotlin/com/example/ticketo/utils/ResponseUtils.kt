package com.example.ticketo.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.ResponseEntity
import java.net.URI

fun buildCreatedResponse(location : String? = null, headerName : String, headerObject : Any) : ResponseEntity<Any> {
    val uri = URI.create(location ?: "")
    val headerObj = jacksonObjectMapper().writeValueAsString(headerObject)
    return ResponseEntity.created(uri).header(headerName, headerObj).build()
}