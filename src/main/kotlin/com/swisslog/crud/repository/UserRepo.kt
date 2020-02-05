package com.swisslog.crud.repository

import com.sun.istack.Nullable
import com.swisslog.crud.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Transactional
@Repository
interface UserRepo: JpaRepository<Users, Int> {
    @Nullable
    @Query("SELECT u FROM Users u WHERE u.userId =:userId")
    fun getUsersById(@Param("userId") userId: Int): Users?

}