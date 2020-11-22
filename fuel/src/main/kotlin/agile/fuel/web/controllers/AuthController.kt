package agile.fuel.web.controllers

import agile.fuel.service.AuthService
import agile.fuel.service.UserService
import agile.fuel.web.dto.LoginDTO
import agile.fuel.web.dto.RegisterUserDTO
import org.apache.coyote.Response
import org.springframework.boot.web.servlet.server.Session
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AuthorizationServiceException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource
import javax.servlet.http.Cookie
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
        @Resource
        val userService: UserService,
        @Resource
        val authService: AuthService,
        @Resource
        val passwordEncoder: BCryptPasswordEncoder
){
    @PostMapping("/register")
    fun register(@Valid @RequestBody registerUser : RegisterUserDTO) : ResponseEntity<Any> {
        userService.createUser(registerUser)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginDTO: LoginDTO) : ResponseEntity<Any>{
        val user = authService.loadUserByUsername(loginDTO.username)
        if(user.password != passwordEncoder.encode(loginDTO.password)){
            throw BadCredentialsException("Invalid login data provided")
        }
        val response = ResponseEntity.ok()
        response.header(HttpHeaders.SET_COOKIE, "xyz")
        return response.body("Login successful")
    }
}