package agile.fuel.service

import agile.fuel.domain.model.UserEntity
import agile.fuel.service.exceptions.FuelException
import agile.fuel.web.dto.RegisterUserDTO
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
interface UserService {
    fun findById(id : String) : UserEntity?
    @Throws(FuelException::class)
    fun createUser(registerUser : RegisterUserDTO)
}