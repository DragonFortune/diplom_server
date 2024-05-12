package diplom.server

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtToken {
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.expiration}")
    private var expiration: Long = 0

    private val key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512)

    fun generateToken(userDetails: UserDetails): String {
        val claims: MutableMap<String, Any> = HashMap()
        return doGenerateToken(claims, userDetails.username)
    }

    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration * 1000))
            .signWith(key)
            .compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expirationDate = extractExpiration(token)
        return expirationDate.before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaims(token).expiration
    }

    private fun extractUsername(token: String): String {
        return extractClaims(token).subject
    }

    private fun extractClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

}