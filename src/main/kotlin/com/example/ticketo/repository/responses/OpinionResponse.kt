package com.example.ticketo.repository.responses

interface OpinionResponse {
    fun getFromUser() : UserResponse
    fun getValue() : String
}