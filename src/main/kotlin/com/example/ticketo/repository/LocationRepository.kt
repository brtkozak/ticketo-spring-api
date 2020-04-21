package com.example.ticketo.repository

import com.example.ticketo.model.Location
import com.example.ticketo.repository.responses.LocationResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LocationRepository : JpaRepository<Location, Int> {

    @Query("SELECT L.city as city FROM Location L")
    fun findAllById(id : Int) : List<LocationResponse>
}