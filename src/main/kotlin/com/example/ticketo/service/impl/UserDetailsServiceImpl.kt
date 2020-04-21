package com.example.ticketo.service.impl

import com.example.ticketo.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository) : UserDetailsService {

    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()

    override fun loadUserByUsername(name: String): UserDetails = userRepository.findUserByLastName(name)

}