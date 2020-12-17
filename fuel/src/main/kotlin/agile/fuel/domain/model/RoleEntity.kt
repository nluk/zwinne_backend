package agile.fuel.domain.model


class RoleEntity(val role : String) : AuditableEntity(){

    enum class Roles(val role : String){
        ADMIN("Admin"),
        USER("User");
        fun entity() : RoleEntity = RoleEntity(role)
    }

}