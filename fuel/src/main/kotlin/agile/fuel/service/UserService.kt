package agile.fuel.service

import agile.fuel.domain.model.UserEntity
import agile.fuel.web.dto.RegisterUserDTO
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun findById(id : String) : UserEntity?
    fun createUser(registerUser : RegisterUserDTO)
}