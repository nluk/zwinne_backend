package agile.fuel.service.impl

import agile.fuel.domain.dao.UserRepository
import agile.fuel.domain.model.RoleEntity
import agile.fuel.domain.model.UserEntity
import agile.fuel.service.UserService
import agile.fuel.web.dto.RegisterUserDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
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

    override fun findById(id: String): UserEntity? = userRepository.findByIdOrNull(id)

    override fun createUser(registerUser: RegisterUserDTO) {
        val user = UserEntity()
        user.email = registerUser.email
        user.password = passwordEncoder.encode(registerUser.password)
        user.login = registerUser.login
        user.grantedRoles = setOf(RoleEntity(RoleEntity.Roles.USER.role))
        userRepository.save(user)
    }


}