package agile.fuel.web.controllers

import agile.fuel.auth.TokenUtil
import agile.fuel.service.AuthService
import agile.fuel.service.UserService
import agile.fuel.service.exceptions.FuelException
import agile.fuel.web.dto.LoginRequestDTO
import agile.fuel.web.dto.LoginResponseDTO
import agile.fuel.web.dto.RegisterUserDTO
import com.google.gson.Gson
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import kotlin.jvm.Throws

@RestController
@RequestMapping("/auth")
class AuthController(
        @Resource
        val userService: UserService,
        @Resource
        val authService: AuthService,
        @Resource
        val passwordEncoder: BCryptPasswordEncoder,
        @Resource
        val tokenUtil: TokenUtil,
        @Resource
        val gson: Gson
){
    @Throws(FuelException::class)
    @PostMapping("/register")
    fun register(@Valid @RequestBody registerUser : RegisterUserDTO) : ResponseEntity<Any> {
        userService.createUser(registerUser)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequestDTO: LoginRequestDTO, servletResponse: HttpServletResponse) : ResponseEntity<Any>{
        val user = authService.loadUserByUsername(loginRequestDTO.username)
        if(!passwordEncoder.matches(loginRequestDTO.password, user.password)){
            throw BadCredentialsException("Invalid login data provided")
        }
        val (expirationDate, token) = tokenUtil.createTokenForClaims(gson.toJson(user))
        servletResponse.addCookie(prepareJWTCookie(token))
        return ResponseEntity.ok(LoginResponseDTO(expirationDate))
    }

    fun prepareJWTCookie(jwt : String) : Cookie{
        val cookie = Cookie("auth", jwt)
        cookie.path = "/fuel"
        //cookie.secure = true
        cookie.isHttpOnly = true
        return cookie
    }
}