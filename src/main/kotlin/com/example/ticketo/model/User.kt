package com.example.ticketo.model

import com.example.ticketo.configuration.SecurityConfig
import com.example.ticketo.repository.responses.UserResponse
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Int = -1,
        @field:NotBlank (message ="First name cannot by empty")
        var firstName : String? =  null,
        @field:NotBlank (message ="Last name cannot by empty")
        var lastName : String? =  null,
        @field:NotBlank (message ="Password cannot by empty")
        var pass : String? = null,
        @field:NotBlank (message ="Email cannot by empty")
        @field:Email (message = "Invalid format")
        var email: String? = null,
        var image : String? =null)
    : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
       return Collections.singleton(SimpleGrantedAuthority(SecurityConfig.Roles.COMMON_USER.name))
    }

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String? = email

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String? = pass

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

}