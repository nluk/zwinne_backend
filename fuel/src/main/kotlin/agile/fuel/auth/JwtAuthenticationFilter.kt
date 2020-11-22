package agile.fuel.auth

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.annotation.Resource
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
        @Resource val userDetailsService: UserDetailsService,
        @Resource val tokenUtil : TokenUtil
) : OncePerRequestFilter() {

    companion object Headers{
        const val BEARER_PREFIX = "Bearer "
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authHeader : String? = request.getHeader(HttpHeaders.AUTHORIZATION)
        if(authHeader?.startsWith(BEARER_PREFIX) == true){
            val token = authHeader.removePrefix(BEARER_PREFIX)
            val username = tokenUtil.extractUsername(token)
            if(SecurityContextHolder.getContext() == null){
                val userDetails = userDetailsService.loadUserByUsername(username)
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}