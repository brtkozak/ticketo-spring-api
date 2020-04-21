package com.example.ticketo.repository.responses

data class UserWithOpinionsResponse(
        val user: UserResponse,
        val opinions : List<OpinionResponse>
)