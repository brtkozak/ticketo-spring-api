package com.example.ticketo.repository

import com.example.ticketo.model.Offer
import com.example.ticketo.repository.responses.FullOfferResponse
import com.example.ticketo.repository.responses.FullOpinionResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OfferRepository : JpaRepository<Offer, Int>{

    @Query("SELECT O.user as user, O.event as event, O.value as value FROM Offer O WHERE O.user.id = :userId and O.event.id = :eventId")
    fun findById(userId : Int, eventId : Int) : FullOfferResponse

    fun existsByUserIdAndEventId(userId: Int, eventId: Int) : Boolean
}