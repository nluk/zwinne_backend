package agile.fuel.auth

import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import java.util.*


class TokenUtil {

    lateinit var parser : JwtParser
    var secret : String = ""

    @Autowired
    fun setSecret(jwtConfigProperties: JwtConfigProperties){
        secret = jwtConfigProperties.secret
    }

    fun extractUsername(tokenString: String) : String{
        return parseClaims(tokenString).subject
    }

    private var parseClaims : (String) -> Claims = {
        parser = Jwts.parser().setSigningKey(secret)
        parseClaims = { token ->
            parser.parseClaimsJws(token).body
        }
        parseClaims(it)
    }

}