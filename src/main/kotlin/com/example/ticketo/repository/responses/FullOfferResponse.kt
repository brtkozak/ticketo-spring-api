package com.example.ticketo.repository.responses

import com.example.ticketo.model.Event

interface FullOfferResponse {
    fun getUser() : UserResponse
    fun getEvent() : Event
    fun getValue() : String
}