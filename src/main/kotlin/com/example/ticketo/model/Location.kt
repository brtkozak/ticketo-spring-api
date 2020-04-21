package com.example.ticketo.model

import javax.persistence.*

@Entity
data class Location (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Int? = null,
        var city : String? = null,
        var street : String? = null,
        var buildingNo : String? = null,
        var flatNo : Int? = null,
        var postalCode : String? = null,
        var lang : String? = null,
        var lat : String? = null
)