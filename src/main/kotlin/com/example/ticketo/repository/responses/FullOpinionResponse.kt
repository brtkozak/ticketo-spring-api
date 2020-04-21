package com.example.ticketo.repository.responses

interface FullOpinionResponse {
    fun getFromUser() : UserResponse
    fun getValue() : String
    fun getToUser() : UserResponse
}