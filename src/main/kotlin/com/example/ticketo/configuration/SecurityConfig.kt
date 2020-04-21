package com.example.ticketo.configuration

import com.example.ticketo.service.impl.UserDetailsServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class SecurityConfig (private val userDetailsService : UserDetailsServiceImpl)  : WebSecurityConfigurerAdapter() {

    enum class Roles {
        COMMON_USER
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/event/*").permitAll()
                .antMatchers("/user/opinion").authenticated()
                .antMatchers("/event/offer").authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
        http.csrf().disable()
    }
}