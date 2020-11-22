package agile.fuel.domain.model

import org.springframework.data.annotation.Id

class RoleEntity(val role : String) : AuditableEntity(){
    @Id
    var id = ""

    enum class Roles(val role : String){
        ADMIN("Admin"),
        USER("User");
    }

}