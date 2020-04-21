package com.example.ticketo.model

import com.example.ticketo.model.validator.OneOf
import java.io.Serializable
import javax.persistence.*

@Entity
@IdClass(OpinionId::class)
data class Opinion(
        @Id
        @ManyToOne
        @JoinColumn(name = "toUser")
        var toUser : User? = null,
        @Id
        @ManyToOne
        @JoinColumn(name = "fromUser")
        var fromUser : User? = null,
        var value : String ? = ""
) : Serializable

data class OpinionId(
        var toUser: Int? = null,
        var fromUser: Int? = null
) : Serializable





