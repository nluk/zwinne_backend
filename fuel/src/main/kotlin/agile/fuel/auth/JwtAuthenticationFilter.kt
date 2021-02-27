package agile.fuel.auth

import com.google.gson.Gson
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
        val tokenUtil : TokenUtil,
        val gson : Gson
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val tokenStr : String? = request.cookies?.firstOrNull { it.name == HttpHeaders.AUTHORIZATION }?.value
        if(tokenStr != null){
            tokenUtil.parseAndValidate(tokenStr)?.subject?.also {
                if(SecurityContextHolder.getContext()?.authentication == null){
                    val userDetails = gson.fromJson(it, AuthUserDetails::class.java)
                    val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                }
            }
        }
        filterChain.doFilter(request, response)
    }
}