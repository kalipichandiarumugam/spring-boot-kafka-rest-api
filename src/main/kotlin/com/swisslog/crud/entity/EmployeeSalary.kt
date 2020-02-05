package com.swisslog.crud.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "employeesalary")
data class EmployeeSalary(@Id
                          @Column(length = 20, name="employee_id", nullable = false, unique = true, updatable = false, insertable = true)
                          val employee_id: Int,
                          @Column(length = 20, name="salary_amount", nullable = false)
                          val salary_amount: Int) {
}