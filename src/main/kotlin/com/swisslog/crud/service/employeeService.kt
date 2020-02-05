package com.swisslog.crud.service

import com.swisslog.crud.entity.Employees
import com.swisslog.crud.repository.EmployeeRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class EmployeeService{

    @Autowired
    lateinit var employeeRepo: EmployeeRepo


    fun getAllEmployees(): List<Employees>{
        return employeeRepo.findAll()
    }

    fun getEmployeeById(employeeId: Int):Employees?{
        return employeeRepo.getEmployeesById(employeeId)
    }

    fun save(employee: Employees){
        employeeRepo.save(employee)
    }
}