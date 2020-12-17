package agile.fuel.auth

import agile.fuel.domain.model.UserEntity
import org.bson.types.ObjectId
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

class AuthUserDetails(userEntity: UserEntity) : UserDetails, Serializable {

    val id : ObjectId
    val login : String
    @Transient
    val pass : String
    val roles : MutableSet<SimpleGrantedAuthority>

    init {
        userEntity.also {
            id = it.id!!
            login = it.login
            pass = it.password
            roles = it.grantedRoles.map { roleEntity -> SimpleGrantedAuthority(roleEntity.role) }.toHashSet()
        }
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles

    override fun getPassword(): String = pass

    override fun getUsername(): String = login

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}