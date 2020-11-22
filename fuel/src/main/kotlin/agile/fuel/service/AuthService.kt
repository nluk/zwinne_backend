package agile.fuel.service

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
interface AuthService : UserDetailsService {}