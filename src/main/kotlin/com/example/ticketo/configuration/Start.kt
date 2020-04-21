package com.example.ticketo.configuration

import com.example.ticketo.repository.UserRepository
import com.sun.tools.corba.se.idl.constExpr.Times
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

@Configuration
class Start (userRepository: UserRepository, passwordEncoder: PasswordEncoder) {

    init {

    }
}