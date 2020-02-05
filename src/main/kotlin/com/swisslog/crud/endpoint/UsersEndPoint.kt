package com.swisslog.crud.endpoint


import com.swisslog.crud.entity.Users
import com.swisslog.crud.service.UserService
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid


@RestController
@RequestMapping("/api/users")
class UsersEndPoint(val userService: UserService) {
    @GetMapping("/getAll", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUsers(): List<Users>{
        return userService.getAllUsers()
    }
    @GetMapping("/{userId}")
    @Throws(NotFoundException::class)
    @ApiResponses(value = [ApiResponse(code = 200, message = "User(s) found", response = Users::class),
        ApiResponse(code = 404, message = "User not found"),
        ApiResponse(code = 405, message = "Not Allowed: Path parameter cannot be empty")])
    fun getEmployeeById(@PathVariable userId: Int): Users?{
        var user: Users? = userService.getUserById(userId)
        if(user!=null)
            return user
        else
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, "user not found"
            )
    }
    @PostMapping("/add", consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ApiResponses(value = [ApiResponse(code = 200, message = "User(s) found", response = Users::class)])
    fun postUser(@RequestBody @Valid user: Users){
        var locUser: Users? = userService.getUserById(user.userId)
        if(locUser!=null)
            throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "user already exists"
            )
        else
            userService.save(user)

    }
}