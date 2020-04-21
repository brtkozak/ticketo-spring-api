package com.example.ticketo.service.impl

import com.example.ticketo.controller.exception.SimpleResponseException
import com.example.ticketo.model.User
import com.example.ticketo.repository.OpinionRepository
import com.example.ticketo.repository.UserRepository
import com.example.ticketo.repository.responses.UserWithOpinionsResponse
import com.example.ticketo.service.IUserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository,
                  private val opinionRepository: OpinionRepository,
                  val passwordEncoder: PasswordEncoder)
    : IUserService {

    override fun registerUser(user: User) : User {
        if(userRepository.findUserByEmail(user.email!!) != null){
            throw SimpleResponseException("User with email ${user.email} already exist.")
        }
        user.pass = passwordEncoder.encode(user.pass)
        return userRepository.save(user)
    }

    override fun getUser(id: Int) : UserWithOpinionsResponse {
        val user = userRepository.findUserById(id) ?: throw SimpleResponseException("User with id $id does not exist")
        val opinions = opinionRepository.findAllByToUserId(id)
        return(UserWithOpinionsResponse(user, opinions))
    }
}