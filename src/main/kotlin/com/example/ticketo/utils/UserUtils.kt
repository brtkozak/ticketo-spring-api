package com.example.ticketo.utils

import com.example.ticketo.model.User
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder

fun getCurrentUserId() : Int {
    val principal = SecurityContextHolder.getContext().authentication.principal
    if(principal is User)
        return principal.id
    else throw AccessDeniedException("Access denied")
}

