package agile.fuel.service.impl


import agile.fuel.auth.AuthUserDetails
import agile.fuel.domain.model.UserEntity
import agile.fuel.service.AuthService
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.where
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service(value = "mongoAuth")
class AuthServiceImpl(
        @Resource
        val mongoTemplate: MongoTemplate
) : AuthService{

    override fun loadUserByUsername(username: String?): UserDetails? {
        val user = mongoTemplate.findOne(Query.query(where(UserEntity::login).`is`(username)), UserEntity::class.java)
        return user?.let{ AuthUserDetails(it) } ?: throw UsernameNotFoundException(username)
    }

}