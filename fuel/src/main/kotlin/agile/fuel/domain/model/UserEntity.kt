package agile.fuel.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "fuel_users")
class UserEntity : AuditableEntity(){
    @Id
    var id = ""
    var login = ""
    var email = ""
    var password = ""
    var grantedRoles : Set<RoleEntity> = emptySet()
}