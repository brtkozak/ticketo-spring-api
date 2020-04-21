package com.example.ticketo.repository

import com.example.ticketo.model.Opinion
import com.example.ticketo.model.User
import com.example.ticketo.repository.responses.FullOpinionResponse
import com.example.ticketo.repository.responses.OpinionResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface OpinionRepository : JpaRepository<Opinion, Int> {

    fun findAllByToUserId(toUserId : Int) : List<OpinionResponse>

    @Transactional
    @Modifying
    @Query("INSERT INTO Opinion VALUES (:toUserId, :fromUserId, :opinion)", nativeQuery =  true)
    fun saveOpinion(toUserId: Int, fromUserId : Int, opinion : String)

    @Query("SELECT O.toUser as toUser, O.fromUser as fromUser, O.value as value FROM Opinion O WHERE O.toUser.id = :toUser and O.fromUser.id = :fromUser")
    fun findById(toUser : Int, fromUser : Int) : FullOpinionResponse

    fun findByToUserIdAndFromUserId(toUserId: Int, fromUser: Int) : Opinion?

    @Transactional
    @Modifying
    @Query("UPDATE Opinion O SET O.value = :value WHERE O.toUser.id = :toUserId and O.fromUser.id = :fromUser")
    fun updateOpinion(toUserId: Int, fromUser: Int, value :String)

}