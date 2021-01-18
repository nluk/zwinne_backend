package agile.fuel.auth

import javax.servlet.http.HttpServletResponse

import javax.servlet.http.HttpServletRequest

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter : Filter {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
        val response = res as HttpServletResponse
        val request = req as HttpServletRequest
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader(
            "Access-Control-Allow-Methods",
            "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL"
        )
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader(
            "Access-Control-Allow-Headers",
            "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization"
        )
        if ("OPTIONS".equals(request.method, ignoreCase = true)) {
            response.status = HttpServletResponse.SC_OK
        } else {
            chain.doFilter(req, res)
        }
    }

    override fun init(filterConfig: FilterConfig?) {
        // not needed
    }

    override fun destroy() {
        //not needed
    }
}