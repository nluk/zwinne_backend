package agile.fuel.domain.dao

import agile.fuel.domain.model.UserEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<UserEntity, String> {
    fun findByEmail(email : String): UserEntity?
}