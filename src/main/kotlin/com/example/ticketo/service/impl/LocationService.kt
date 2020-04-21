package com.example.ticketo.service.impl

import com.example.ticketo.model.Location
import com.example.ticketo.repository.EventRepository
import com.example.ticketo.repository.LocationRepository
import com.example.ticketo.repository.responses.LocationResponse
import com.example.ticketo.service.IEventService
import com.example.ticketo.service.ILocationService
import org.springframework.stereotype.Service

@Service
class LocationService (private val locationRepository : LocationRepository) : ILocationService {

    override fun getCities() : List<LocationResponse> {
        return locationRepository.findAllById(1)
    }
}