package agile.fuel.auth

import agile.fuel.domain.model.UserEntity
import agile.fuel.service.UserService
import io.jsonwebtoken.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

import agile.fuel.plusMillis;
import io.jsonwebtoken.security.Keys

import io.jsonwebtoken.io.Decoders
import java.security.Key


@Component
class TokenUtil(@Autowired jwtConfigProperties: JwtConfigProperties) {

    val logger: Logger = LoggerFactory.getLogger(TokenUtil::class.java)

    val issuer = jwtConfigProperties.issuer
    val key : Key = jwtConfigProperties.secret.let { Decoders.BASE64.decode(it) }.let { Keys.hmacShaKeyFor(it) }
    var parser : JwtParser = Jwts.parserBuilder().setSigningKey(jwtConfigProperties.secret).build()
    val tokenLifetime : Long = jwtConfigProperties.tokenLifetime

    fun parseAndValidate(tokenString: String) : Claims?{
        try {
            return parser.parseClaimsJws(tokenString).body
        } catch (ex : SignatureException) {
            logger.error("Invalid JWT signature - {}", ex.message);
        } catch (ex : MalformedJwtException) {
            logger.error("Invalid JWT token - {}", ex.message);
        } catch (ex : ExpiredJwtException) {
            logger.error("Expired JWT token - {}", ex.message);
        } catch (ex : UnsupportedJwtException) {
            logger.error("Unsupported JWT token - {}", ex.message);
        } catch (ex : IllegalArgumentException) {
            logger.error("JWT claims string is empty - {}", ex.message);
        }
        return null
    }

    fun createTokenForClaims(claims : String) : Pair<Date, String>{
        val now = Date()
        val expiration = now plusMillis tokenLifetime
        return Pair(expiration,  Jwts.builder()
            .setSubject(claims)
            .setIssuer(issuer)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(key, SignatureAlgorithm.HS512)
            .compact())
    }


}