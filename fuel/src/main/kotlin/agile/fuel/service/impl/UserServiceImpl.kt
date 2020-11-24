package agile.fuel.service.impl

import agile.fuel.domain.dao.UserRepository
import agile.fuel.domain.model.RoleEntity
import agile.fuel.domain.model.UserEntity
import agile.fuel.service.UserService
import agile.fuel.service.exceptions.FuelErrorType
import agile.fuel.service.exceptions.FuelException
import agile.fuel.web.dto.RegisterUserDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime
import javax.annotation.Resource


@Service
class UserServiceImpl(
        @Resource
        val userRepository: UserRepository,
        @Resource
        val mongoTemplate: MongoTemplate,
        @Resource
        val passwordEncoder: BCryptPasswordEncoder
) : UserService {

    val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    override fun findById(id: String): UserEntity? = userRepository.findByIdOrNull(id)

    override fun createUser(registerUser: RegisterUserDTO) {
        val user = UserEntity()
        user.created = LocalDateTime.now()
        user.email = registerUser.email
        user.password = passwordEncoder.encode(registerUser.password)
        user.login = registerUser.login
        user.grantedRoles = setOf(RoleEntity.Roles.USER.entity())
        try{
            userRepository.save(user)
        }catch (e : DuplicateKeyException){
            throw FuelException(FuelErrorType.DUPLICATED_EMAIL, registerUser.email)
        }catch (e : Exception){
            logger.error("Failed to register due to: ${e.message}")
            throw FuelException(FuelErrorType.UNKNOWN_ERROR)
        }

    }


}