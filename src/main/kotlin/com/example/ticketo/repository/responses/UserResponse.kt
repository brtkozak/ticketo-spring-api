package com.example.ticketo.repository.responses

interface UserResponse {
    fun getId() : Int
    fun getFirstName() : String
    fun getLastName() : String
    fun getImage() : String
    fun getEmail () : String
}