package com.example.ticketo.controller

import com.example.ticketo.controller.request_dto.OpinionRequest
import com.example.ticketo.utils.buildCreatedResponse
import com.example.ticketo.utils.getCurrentUserId
import com.example.ticketo.model.User
import com.example.ticketo.service.IOpinionService
import com.example.ticketo.service.IUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController (private val userService : IUserService, private val opinionService : IOpinionService) {

    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody user : User) : ResponseEntity<Any> {
        val savedUser = userService.registerUser(user)
        val location = "/user/${savedUser.id}"
        return buildCreatedResponse(location, "user", savedUser)
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId : Int) : ResponseEntity<Any>{
        val user = userService.getUser(userId)
        return ResponseEntity.ok(user)
    }

    @GetMapping("/{userId}/opinion")
    fun getUserOpinions(@PathVariable userId : Int) : ResponseEntity<Any>{
        val opinions = opinionService.getUserOpinions(userId)
        return ResponseEntity.ok(opinions)
    }

    @PostMapping("/opinion")
    fun addUserOpinion(@Valid @RequestBody opinionRequest : OpinionRequest) : ResponseEntity<Any>{
        val opinion = opinionService.addUserOpinion(opinionRequest)
        val location = "/user/${opinionRequest.toUser}/opinion/${getCurrentUserId()}"
        return buildCreatedResponse(location, "opinion", opinion)
    }

    @DeleteMapping("/{toUser}/opinion/{fromUser}")
    fun deleteOpinion(@PathVariable toUser: Int, @PathVariable fromUser: Int): ResponseEntity<Any> {
        val result = opinionService.deleteOpinion(toUser, fromUser)
        if (result)
            return ResponseEntity.ok("Opinion deleted")
        else
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Opinion not found")
    }

    @PutMapping("/{toUser}/opinion/{fromUser}")
    fun updateOpinion(@RequestBody value : String, @PathVariable toUser: Int, @PathVariable fromUser: Int) : ResponseEntity<Any>{
        val opinionRequest = OpinionRequest(toUser, value)
        val status = opinionService.updateOpinion(opinionRequest, fromUser)
        return ResponseEntity(status)
    }

}