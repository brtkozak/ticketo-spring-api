package com.example.ticketo.repository

import com.example.ticketo.model.User
import com.example.ticketo.repository.responses.UserResponse
import com.example.ticketo.repository.responses.UserWithOpinionsResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {

    fun findUserByLastName(lastName : String) : User
    fun findUserByEmail(email : String) : User?

    @Query("SELECT U.id as id, U.firstName as firstName, U.lastName as lastName, U.email as email, U.image as image FROM User U WHERE U.id=:id")
    fun findUserById(id : Int) : UserResponse?
}