package goal.jalal.goaljalal.auth.jwt;

import goal.jalal.goaljalal.auth.exception.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private static final String ID_KEY = "kakaoId";

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
        final Date expiryDate = new Date(now.getTime() + jwtAccessTokenExpirationInMs);
        final Key secretKey = Keys.hmacShaKeyFor(jwtAccessTokenSecret.getBytes());

        return Jwts.builder()
            .claim(ID_KEY, id)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey)
            .compact();
    }

    public Long extractKakaoIdFromAccessToken(final String token) {
        validateAccessToken(token);
        final Jws<Claims> claimsJws = getAccessTokenParser().parseClaimsJws(token);
        Long extractedKakaoId = claimsJws.getBody().get(ID_KEY, Long.class);
        if (extractedKakaoId == null) {
            throw new AuthenticationException.AccessTokenClaimNullException(token);
        }
        return extractedKakaoId;
    }

    private void validateAccessToken(final String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getAccessTokenSecretKey())
                .build().parseClaimsJws(token).getBody();
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            throw new AuthenticationException.InvalidAccessTokenException(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "expired access token");
        }
    }

    private JwtParser getAccessTokenParser() {
        return Jwts.parserBuilder()
            .setSigningKey(getAccessTokenSecretKey())
            .build();
    }

    private Key getAccessTokenSecretKey() {
        return Keys.hmacShaKeyFor(jwtAccessTokenSecret.getBytes());
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

    public Long extractKakaoIdFromRefreshToken(final String token) {
        validateRefreshToken(token);
        final Jws<Claims> claimsJws = getRefreshTokenParser().parseClaimsJws(token);
        Long extractedKakaoId = claimsJws.getBody().get(ID_KEY, Long.class);
        if (extractedKakaoId == null) {
            throw new AuthenticationException.RefreshTokenClaimNullException(token);
        }
        return extractedKakaoId;
    }

    private void validateRefreshToken(final String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getRefreshTokenSecretKey())
                .build().parseClaimsJws(token).getBody();
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            throw new AuthenticationException.InvalidRefreshTokenException(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "expired access token");
        }
    }

    private JwtParser getRefreshTokenParser() {
        return Jwts.parserBuilder()
            .setSigningKey(getRefreshTokenSecretKey())
            .build();
    }

    private Key getRefreshTokenSecretKey() {
        return Keys.hmacShaKeyFor(jwtRefreshTokenSecret.getBytes());
    }

}
