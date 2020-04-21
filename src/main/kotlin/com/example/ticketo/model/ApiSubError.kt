package com.example.ticketo.model

data class ApiSubError(
        val obj : String? =null,
        val field : String? = null,
        val rejectedValue : Any? = null,
        val message : String? = null)