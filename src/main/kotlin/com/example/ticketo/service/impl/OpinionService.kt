package com.example.ticketo.service.impl

import com.example.ticketo.controller.exception.SimpleResponseException
import com.example.ticketo.controller.request_dto.OpinionRequest
import com.example.ticketo.controller.request_dto.OpinionValue
import com.example.ticketo.utils.getCurrentUserId
import com.example.ticketo.model.Opinion
import com.example.ticketo.repository.OpinionRepository
import com.example.ticketo.repository.UserRepository
import com.example.ticketo.repository.responses.FullOpinionResponse
import com.example.ticketo.repository.responses.OpinionResponse
import com.example.ticketo.service.IOpinionService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.validation.Valid


@Service
class OpinionService (private val opinionRepository: OpinionRepository, private val userRepository: UserRepository) : IOpinionService{

    override fun getUserOpinions(userId: Int): List<OpinionResponse> {
        return opinionRepository.findAllByToUserId(userId)
    }

    override fun addUserOpinion(opinionRequest: OpinionRequest) : FullOpinionResponse {
        val currentUserId = getCurrentUserId()
        val currentUser = userRepository.findById(currentUserId)
        val toUser = userRepository.findById(opinionRequest.toUser)
        if(!currentUser.isPresent)
            throw SimpleResponseException("User with id $currentUserId does not exist")
        if(!toUser.isPresent)
            throw SimpleResponseException("User with id ${opinionRequest.toUser} does not exist")
        try {
            opinionRepository.save(Opinion(toUser.get(), currentUser.get(), opinionRequest.value))
            return opinionRepository.findById(opinionRequest.toUser, currentUser.get().id)
        }
        catch(e: DataIntegrityViolationException) {
            throw SimpleResponseException("User can add only one opinion to another user.", HttpStatus.CONFLICT)
        }
    }

    override fun deleteOpinion(toUser: Int, fromUser: Int) : Boolean {
        if(getCurrentUserId() != fromUser)
            throw SimpleResponseException("User is not allowed to delete this opinion", HttpStatus.FORBIDDEN)
        val opinion = opinionRepository.findByToUserIdAndFromUserId(toUser, fromUser)
        return if(opinion != null) {
            opinionRepository.delete(opinion)
            true
        }
        else false;
    }

    override fun updateOpinion(@Valid opinionRequest: OpinionRequest, fromUser : Int) : HttpStatus{
        val allowedValues = OpinionValue.values().map { it -> it.name.toLowerCase() }
        if(!allowedValues.contains(opinionRequest.value)){
            throw SimpleResponseException("Value must be one of [positive; negative]")
        }
        if(getCurrentUserId() != fromUser)
            throw SimpleResponseException("User not allowed to modify this opinion", HttpStatus.FORBIDDEN)
        val opinion = opinionRepository.findByToUserIdAndFromUserId(opinionRequest.toUser, fromUser)
        return if(opinion != null){
            opinionRepository.updateOpinion(opinionRequest.toUser, fromUser, opinionRequest.value)
            HttpStatus.OK
        }
        else {
            addUserOpinion(opinionRequest)
            HttpStatus.CREATED
        }
    }

}