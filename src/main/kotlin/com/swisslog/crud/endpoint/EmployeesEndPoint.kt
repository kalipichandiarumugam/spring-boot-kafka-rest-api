package com.swisslog.crud.endpoint

import com.swisslog.crud.entity.Employees
import com.swisslog.crud.service.EmployeeService
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*


@RestController
@RequestMapping("/api/employees")
class EmployeesEndPoint(
        @Autowired
        @Qualifier(value = "crudProducer")
        var employeeProducerTemplate: KafkaTemplate<String, Employees>){

    @Autowired
    lateinit var employeeService: EmployeeService

    @GetMapping("/getAll", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getEmployees(): List<Employees>{
        return employeeService.getAllEmployees()
    }
    @GetMapping("/{employeeId}")
    @Throws(NotFoundException::class)
    @ApiResponses(value = [ApiResponse(code = 200, message = "Employee(s) found", response = Employees::class),
        ApiResponse(code = 404, message = "Employee not found"),
        ApiResponse(code = 405, message = "Not Allowed: Path parameter cannot be empty")])
    fun getEmployeeById(@PathVariable employeeId: Int): Employees?{
        var employee: Employees? = employeeService.getEmployeeById(employeeId)
        if(employee!=null)
            return employee
        else
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found")
    }
    @PostMapping("/add", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun postEmployee(@RequestBody employee: Employees){
        employeeProducerTemplate.send("NewEmployee", UUID.randomUUID().toString(), employee)
        employeeService.save(employee)
    }
}