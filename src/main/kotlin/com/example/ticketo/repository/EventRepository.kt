package com.example.ticketo.repository

import com.example.ticketo.model.Artist
import com.example.ticketo.model.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.sql.Timestamp

@Repository
interface EventRepository : JpaRepository<Event, Int> {
    fun findNameById(id : Int) : Event
    fun findAllByArtistName(artistName : String) : List<Event>
    fun findAllByDateAfter(date : Timestamp) : List<Event>
    fun findAllByDateBefore(date : Timestamp) : List<Event>
    fun findAllByLocationCity(city : String) : List<Event>
    fun findAllByArtistNameAndDateBefore(artistName: String, date: Timestamp) : List<Event>
    fun findAllByArtistNameAndDateAfter(artistName: String, date: Timestamp) : List<Event>
    fun findAllByArtistNameAndLocationCity(artistName: String, city: String) : List<Event>
    fun findAllByDateBetween(dateFrom : Timestamp, dateTo : Timestamp) : List<Event>
    fun findAllByDateAfterAndLocationCity(dateFrom: Timestamp, city: String) : List<Event>
    fun findAllByDateBeforeAndLocationCity(dateTo: Timestamp, city: String) : List<Event>
    fun findAllByArtistNameAndDateBetween(artistName: String, dateFrom: Timestamp, dateTo: Timestamp) : List<Event>
    fun findAllByArtistNameAndDateAfterAndLocationCity(artistName: String, dateFrom: Timestamp, city: String) : List<Event>
    fun findAllByArtistNameAndDateBeforeAndLocationCity(artistName: String, dateTo: Timestamp, city: String) : List<Event>
    fun findAllByDateBetweenAndLocationCity(dateFrom: Timestamp, dateTo: Timestamp, city: String) : List<Event>
    fun findAllByArtistNameAndDateBetweenAndLocationCity(artistName: String, dateFrom: Timestamp, dateTo: Timestamp, city: String) : List<Event>
    fun findAllByNameContains(name : String) : List<Event>
}