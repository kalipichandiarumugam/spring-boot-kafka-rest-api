package com.swisslog.crud.entity

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "employees")
data class Employees(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(length = 20, name="employee_id", nullable = false, unique = true, updatable = false, insertable = false)
        val employeeId: Int = 1,
        @Column(length = 255, name="employee_name", nullable = false)
        val employeeName: String = "",
        @Column(name="dob", nullable = false)
        val dob: Instant = Instant.now(),
        @Column(name="hired_date", nullable = false)
        val hiredDate: Instant = Instant.now())