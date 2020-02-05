package com.swisslog.crud.entity

import javax.persistence.*

@Entity
@Table(name = "departments")
data class Departments(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(length = 20, name="department_id", nullable = false, unique = true, updatable = false, insertable = false)
        val departmentId: Int,
        @Column(length = 20, name="department_name", nullable = false)
        val departmentName: String)