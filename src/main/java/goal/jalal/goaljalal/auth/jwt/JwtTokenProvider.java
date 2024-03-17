package goal.jalal.goaljalal.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String ID_KEY = "kakaoId";

    @Value("${jwt.access.secret}")
    private String jwtAccessTokenSecret;
    @Value("${jwt.access.expiration}")
    private long jwtAccessTokenExpirationInMs;

    @Value("${jwt.refresh.secret}")
    private String jwtRefreshTokenSecret;
    @Value("${jwt.refresh.expiration}")
    private long jwtRefreshTokenExpirationInMs;

    public String generateAccessToken(long id) {
        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime() + jwtRefreshTokenExpirationInMs);
        final Key secretKey = Keys.hmacShaKeyFor(jwtAccessTokenSecret.getBytes());

        return Jwts.builder()
            .claim(ID_KEY, id)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey)
            .compact();
    }

    public String generateRefreshToken(long id) {
        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime() + jwtRefreshTokenExpirationInMs);
        final Key secretKey = Keys.hmacShaKeyFor(jwtRefreshTokenSecret.getBytes());

        return Jwts.builder()
            .claim(ID_KEY, id)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey)
            .compact();
    }
}
