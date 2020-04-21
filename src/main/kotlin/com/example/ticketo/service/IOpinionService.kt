package com.example.ticketo.service

import com.example.ticketo.controller.request_dto.OpinionRequest
import com.example.ticketo.model.Opinion
import com.example.ticketo.repository.responses.FullOpinionResponse
import com.example.ticketo.repository.responses.OpinionResponse
import org.springframework.http.HttpStatus
import javax.validation.Valid

interface IOpinionService {
    fun getUserOpinions(userId : Int) : List<OpinionResponse>
    fun addUserOpinion(opinionRequest: OpinionRequest) : FullOpinionResponse
    fun deleteOpinion(toUser : Int, fromUser: Int) : Boolean
    fun updateOpinion(@Valid opinionRequest: OpinionRequest, fromUser : Int): HttpStatus
}