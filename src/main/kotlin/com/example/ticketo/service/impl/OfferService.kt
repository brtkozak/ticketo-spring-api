package com.example.ticketo.service.impl

import com.example.ticketo.controller.exception.SimpleResponseException
import com.example.ticketo.controller.request_dto.OfferRequest
import com.example.ticketo.controller.request_dto.OpinionRequest
import com.example.ticketo.model.Offer
import com.example.ticketo.model.Opinion
import com.example.ticketo.repository.EventRepository
import com.example.ticketo.repository.OfferRepository
import com.example.ticketo.repository.UserRepository
import com.example.ticketo.repository.responses.FullOfferResponse
import com.example.ticketo.service.IOfferService
import com.example.ticketo.utils.getCurrentUserId
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class OfferService(private val offerRepository : OfferRepository,
                   private val userRepository : UserRepository,
                   private val eventRepository: EventRepository) : IOfferService{

    override fun addOffer(offerRequest: OfferRequest): FullOfferResponse {
        val currentUserId = getCurrentUserId()
        val currentUser = userRepository.findById(currentUserId)
        val event = eventRepository.findById(offerRequest.event)
        if(!currentUser.isPresent)
            throw SimpleResponseException("User with id $currentUserId does not exist")
        if(!event.isPresent)
            throw SimpleResponseException("Event with id ${offerRequest.event} does not exist")
        val offer = Offer(currentUser.get(), event.get(), offerRequest.value)
        if(offerRepository.existsByUserIdAndEventId(currentUserId, offer.event?.id!!)) {
            throw SimpleResponseException("User can add only one offer to event.", HttpStatus.CONFLICT)
        }
        else{
            offerRepository.save(Offer(currentUser.get(), event.get(), offerRequest.value))
            return offerRepository.findById(currentUserId, event.get().id!!)
        }
    }

}