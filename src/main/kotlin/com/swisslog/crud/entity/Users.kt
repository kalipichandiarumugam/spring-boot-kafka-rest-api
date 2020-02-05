package com.swisslog.crud.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Entity
@Table(name = "users")
data class Users(
        @Id
        @Column(length = 20, name="user_id", nullable = false, unique = true, updatable = false, insertable = true)
        val userId: Int = 1,
        @Column(length = 20, name="user_name", nullable = false)
        @Pattern(regexp = "^[a-zA-Z]$")
        @NotBlank(message = "User name is mandatory")
        val userName: String = "")