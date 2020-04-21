package com.example.ticketo.controller

import com.example.ticketo.controller.request_dto.OfferRequest
import com.example.ticketo.service.IEventService
import com.example.ticketo.service.IOfferService
import com.example.ticketo.utils.buildCreatedResponse
import com.example.ticketo.utils.getCurrentUserId
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/event")
class EventController (private val eventService: IEventService, private val offerService : IOfferService) {

    @GetMapping("/{eventId}")
    fun getEvent(@PathVariable eventId : Int) = ResponseEntity.ok(eventService.getEvent(eventId))

    @GetMapping("")
    fun getEvents(
            @RequestParam (required = false) artist : String?,
            @RequestParam ("date_from", required = false) dateFrom : String?,
            @RequestParam ("date_to", required = false) dateTo : String?,
            @RequestParam (required = false) city : String?): ResponseEntity<Any>{

        val filters = mutableListOf<Pair<Filter, String>>()
        if(artist != null && artist.isNotEmpty()){
            filters.add(Pair(Filter.ARTIST, artist))
        }
        if(dateFrom != null && dateFrom.isNotEmpty()){
            filters.add(Pair(Filter.DATE_FROM, dateFrom))
        }
        if(dateTo != null && dateTo.isNotEmpty()){
            filters.add(Pair(Filter.DATE_TO, dateTo))
        }
        if(city != null && city.isNotEmpty()){
            filters.add(Pair(Filter.CITY, city))
        }

        val result = eventService.getEvents(filters)
        return if(result != null)
            ResponseEntity.ok(result)
        else ResponseEntity.badRequest().build()
    }

    @GetMapping("/search")
    fun searchEventByPhrase(@RequestParam q : String) : ResponseEntity<Any>{
        val result = eventService.getEventsByPhrase(q)
        return if(result != null)
            ResponseEntity.ok(result)
        else ResponseEntity(INTERNAL_SERVER_ERROR)
    }

    @PostMapping("/offer")
    fun addOffer(@Valid @RequestBody offerRequest : OfferRequest) : ResponseEntity<Any> {
        val offer = offerService.addOffer(offerRequest)
        val location = "/event/${offerRequest.event}/offer/${getCurrentUserId()}"
        return buildCreatedResponse(location, "offer", offer)
    }
}

enum class Filter{
    ARTIST, DATE_FROM, DATE_TO, CITY
}