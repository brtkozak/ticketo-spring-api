package com.example.ticketo.utils

import com.example.ticketo.controller.exception.SimpleResponseException
import org.springframework.http.HttpStatus
import java.sql.Date
import java.sql.Timestamp

fun String.toTimestamp() : Timestamp{
    try {
        val d = Date.valueOf(this)
        return Timestamp(d.time)
    }
    catch (e : Exception){
        throw SimpleResponseException("Invalid date format. Acceptable: yyyy-MM-dd", HttpStatus.BAD_REQUEST)
    }
}