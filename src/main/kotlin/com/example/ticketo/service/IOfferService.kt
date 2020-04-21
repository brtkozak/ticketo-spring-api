package com.example.ticketo.service

import com.example.ticketo.controller.request_dto.OfferRequest
import com.example.ticketo.repository.responses.FullOfferResponse

interface IOfferService {
    fun addOffer(offerRequest : OfferRequest) : FullOfferResponse
}