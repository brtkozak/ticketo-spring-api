package com.example.ticketo.service

import com.example.ticketo.controller.Filter
import com.example.ticketo.model.Event
import org.springframework.stereotype.Component

@Component
interface IEventService {
    fun getEvent(id : Int) : Event
    fun getEvents(filters : List<Pair<Filter, String>>) : List<Event>?
    fun getEventsByPhrase(phrase : String) : List<Event>?
}