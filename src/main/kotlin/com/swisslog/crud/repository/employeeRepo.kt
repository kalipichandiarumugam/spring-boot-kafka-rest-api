package com.swisslog.crud.repository


import com.sun.istack.Nullable
import com.swisslog.crud.entity.Employees
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional


@Transactional
@Repository
interface EmployeeRepo: JpaRepository<Employees, Int> {
    @Nullable
    @Query("SELECT emp FROM Employees emp WHERE emp.employeeId =:employeeId")
    fun getEmployeesById(@Param("employeeId") employeeId: Int): Employees?

}