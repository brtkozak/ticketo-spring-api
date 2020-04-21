package com.example.ticketo.controller.exception

import org.springframework.http.HttpStatus

class SimpleResponseException(override val message : String, val status : HttpStatus = HttpStatus.BAD_REQUEST) : Exception()