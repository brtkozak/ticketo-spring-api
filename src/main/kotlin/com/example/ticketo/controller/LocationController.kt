package com.example.ticketo.controller

import com.example.ticketo.service.ILocationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/location")
class LocationController (private val locationService : ILocationService) {

    @GetMapping("/city")
    fun getCities() = locationService.getCities()

}