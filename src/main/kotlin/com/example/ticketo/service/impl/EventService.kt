package com.example.ticketo.service.impl

import com.example.ticketo.controller.Filter
import com.example.ticketo.controller.exception.SimpleResponseException
import com.example.ticketo.model.Event
import com.example.ticketo.repository.EventRepository
import com.example.ticketo.service.IEventService
import com.example.ticketo.utils.toTimestamp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.sql.Timestamp

@Service
class EventService (private val eventRepository : EventRepository) : IEventService {

    override fun getEvent(id: Int): Event = eventRepository.findById(id).orElseThrow { SimpleResponseException(
            "Event not found", HttpStatus.NOT_FOUND
    ) }

    override fun getEvents(filters: List<Pair<Filter, String>>): List<Event>? {

        if (filters.isEmpty())
            return eventRepository.findAll()

        var artist: String? = null
        filters.firstOrNull { it.first == Filter.ARTIST }?.let {
            artist = it.second
        }
        var dateFrom: Timestamp? = null
        filters.firstOrNull { it.first == Filter.DATE_FROM }?.let {
            dateFrom = it.second.toTimestamp()
        }
        var dateTo: Timestamp? = null
        filters.firstOrNull { it.first == Filter.DATE_TO }?.let {
            dateTo = it.second.toTimestamp()
        }
        var city: String? = null
        filters.firstOrNull { it.first == Filter.CITY }?.let {
            city = it.second
        }

        if (filters.size == 1) {
            when {
                artist != null -> return eventRepository.findAllByArtistName(artist!!)
                dateFrom != null -> return eventRepository.findAllByDateAfter(dateFrom!!)
                dateTo != null -> return eventRepository.findAllByDateBefore(dateTo!!)
                city != null -> return eventRepository.findAllByLocationCity(city!!)
            }
        } else if (filters.size == 2) {
            if (artist != null) {
                when {
                    dateFrom != null -> return eventRepository.findAllByArtistNameAndDateAfter(artist!!, dateFrom!!)
                    dateTo != null -> return eventRepository.findAllByArtistNameAndDateAfter(artist!!, dateTo!!)
                    city != null -> return eventRepository.findAllByArtistNameAndLocationCity(artist!!, city!!)
                }
            } else if (dateFrom != null) {
                when {
                    dateTo != null -> return eventRepository.findAllByDateBetween(dateFrom!!, dateTo!!)
                    city != null -> return eventRepository.findAllByDateAfterAndLocationCity(dateFrom!!, city!!)
                }
            } else if (dateTo != null) {
                if (city != null) {
                    return eventRepository.findAllByDateBeforeAndLocationCity(dateTo!!, city!!)
                }
            }
        }

        if (filters.size == 3) {
            if (artist != null) {
                if (dateFrom != null) {
                    if (dateTo != null) {
                        return eventRepository.findAllByArtistNameAndDateBetween(artist!!, dateFrom!!, dateTo!!)
                    } else if (city != null) {
                        return eventRepository.findAllByArtistNameAndDateAfterAndLocationCity(artist!!, dateFrom!!, city!!)
                    }
                } else if (dateTo != null) {
                    if (city != null) {
                        return eventRepository.findAllByArtistNameAndDateBeforeAndLocationCity(artist!!, dateTo!!, city!!)
                    }
                }
            } else if (dateFrom != null) {
                if (dateTo != null) {
                    if (city != null) {
                        return eventRepository.findAllByDateBetweenAndLocationCity(dateFrom!!, dateTo!!, city!!)
                    }
                }
            }
        } else if (filters.size == 4) {
            if (artist != null && dateFrom != null && dateTo != null && city != null)
                return eventRepository.findAllByArtistNameAndDateBetweenAndLocationCity(artist!!, dateFrom!!, dateTo!!, city!!)
        }

        return null
    }

    override fun getEventsByPhrase(phrase: String): List<Event>? {
        return eventRepository.findAllByNameContains(phrase)
    }
}