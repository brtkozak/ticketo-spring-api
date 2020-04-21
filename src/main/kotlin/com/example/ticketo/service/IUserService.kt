package com.example.ticketo.service

import com.example.ticketo.model.User
import com.example.ticketo.repository.responses.UserResponse
import com.example.ticketo.repository.responses.UserWithOpinionsResponse


interface IUserService {
    fun registerUser(user : User) : User
    fun getUser(id : Int) : UserWithOpinionsResponse
}