package agile.fuel.auth

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
class JwtConfigProperties {
    var secret = ""
    var issuer = ""
    var tokenLifetime = 0L
}