package mate.academy.springbootwebgreqit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import java.util.logging.Logger;

@Component
public class JwtUtil {
    private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());
    private final Key secret;
    @Value("${jwt.expiration}")
    private long expiration;

    public JwtUtil(@Value("${jwt.secret}") String secretString) {
        this.secret = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secret)
                .compact();
    }


    public boolean isValidToken(String token) {
        try {
            Jws<Claims> claimsJwts = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            boolean isExpired = claimsJwts.getBody().getExpiration().before(new Date());
            if (isExpired) {
                logger.warning("Token is expired");
            }
            return !isExpired;
        } catch (JwtException e) {
            logger.severe("Invalid JWT token: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            logger.severe("Token is null or empty: " + e.getMessage());
            return false;
        }
    }

    public String getUsername(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final  Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
