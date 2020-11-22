package agile.fuel.auth

import agile.fuel.domain.model.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthUserDetails(userEntity: UserEntity) : UserDetails {

    val id : String
    val login : String
    val pass : String
    val roles : MutableSet<GrantedAuthority>

    init {
        userEntity.also {
            id = it.id
            login = it.login
            pass = it.password
            roles = it.grantedRoles.map { roleEntity -> GrantedAuthority { roleEntity.role } }.toHashSet()
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