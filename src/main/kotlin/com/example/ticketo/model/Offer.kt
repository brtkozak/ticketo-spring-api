package com.example.ticketo.model

import java.io.Serializable
import javax.persistence.*

@Entity
@IdClass(OfferId::class)
data class Offer (
        @Id
        @ManyToOne
        @JoinColumn(name = "userId")
        var user : User? = null,
        @Id
        @ManyToOne
        @JoinColumn(name = "eventId")
        var event : Event? = null,
        var value : String ? = ""
) : Serializable

data class OfferId(
        var user : Int? = null,
        var event : Int? = null
) : Serializable