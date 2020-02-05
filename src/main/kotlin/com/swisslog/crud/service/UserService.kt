package com.swisslog.crud.service


import com.swisslog.crud.entity.Users
import com.swisslog.crud.repository.UserRepo
import org.springframework.stereotype.Service

@Service
class UserService(val userRepo: UserRepo) {
    fun getAllUsers(): List<Users>{
        return userRepo.findAll()
    }

    fun getUserById(userId: Int): Users?{
        return userRepo.getUsersById(userId)
    }

    fun save(user: Users){
        userRepo.save(user)
    }
}