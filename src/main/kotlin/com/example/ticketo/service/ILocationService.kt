package com.example.ticketo.service

import com.example.ticketo.model.Location
import com.example.ticketo.repository.responses.LocationResponse

interface ILocationService {
    fun getCities() : List<LocationResponse>
}