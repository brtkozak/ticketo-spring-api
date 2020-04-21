package com.example.ticketo.model

import java.sql.Timestamp
import javax.persistence.*

@Entity
data class Event(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        @ManyToOne
        @JoinColumn(name = "locationId")
        var location : Location? = null,
        var name : String? = null,
        var date : Timestamp? = null,
        @ManyToOne
        @JoinColumn(name = "artistId")
        var artist : Artist? = null
)