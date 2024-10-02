package site.yacht.backend.domain.authentication.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import site.yacht.backend.domain.authentication.domain.GrantType;
import site.yacht.backend.domain.authentication.domain.RefreshToken;
import site.yacht.backend.domain.authentication.domain.TokenType;
import site.yacht.backend.domain.authentication.dto.TokenDto;
import site.yacht.backend.domain.authentication.exception.InvalidTokenException;
import site.yacht.backend.global.service.DateTimeService;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class TokenManager {

    private final Key key;
    private final String issuer;
    private final String tokenTypeHeaderKey;
    private final Long accessTokenValidityInMilliseconds;
    private final Long refreshTokenValidityInMilliseconds;
    private final UserDetailsService userDetailsService;
    private final DateTimeService dateTimeService;

    public TokenManager(@Value("${token.secret}") String secretKey,
                        @Value("${token.issuer}") String issuer,
                        @Value("${token.access-token-expiration-seconds}") Long accessTokenExpirationSeconds,
                        @Value("${token.refresh-token-expiration-seconds}") Long refreshTokenExpirationSeconds,
                        UserDetailsService userDetailsService,
                        DateTimeService dateTimeService) {
        this.accessTokenValidityInMilliseconds = accessTokenExpirationSeconds * 1000;
        this.refreshTokenValidityInMilliseconds = refreshTokenExpirationSeconds * 1000;
        this.issuer = issuer;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenTypeHeaderKey = "tokentype";
        this.userDetailsService = userDetailsService;
        this.dateTimeService = dateTimeService;
    }

    public TokenDto createTokenDto(String username) {
        long now = new Date().getTime();
        Date accessTokenExpiredAt = createAccessTokenExpirationTime(now);
        Date refreshTokenExpiredAt = createRefreshTokenExpirationTime(now);

        String accessToken = createAccessToken(username, accessTokenExpiredAt);
        String refreshToken = createRefreshTokenEntity(username, refreshTokenExpiredAt);

        return new TokenDto(GrantType.BEARER.getType(), accessToken, refreshToken, accessTokenExpiredAt, refreshTokenExpiredAt);
    }

    private Date createAccessTokenExpirationTime(long now) {
        return new Date(now + accessTokenValidityInMilliseconds);
    }

    private Date createRefreshTokenExpirationTime(long now) {
        return new Date(now + refreshTokenValidityInMilliseconds);
    }

    private String createAccessToken(String username, Date expireDate) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setExpiration(expireDate)
                .setSubject(username)
                .signWith(key, SignatureAlgorithm.HS256)
                .setHeaderParam(tokenTypeHeaderKey, TokenType.ACCESS.name())
                .compact();
    }

    private String createRefreshTokenEntity(String username, Date expireDate) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(issuer)
                .setExpiration(expireDate)
                .setSubject(username)
                .signWith(key, SignatureAlgorithm.HS256)
                .setHeaderParam(tokenTypeHeaderKey, TokenType.REFRESH.name())
                .compact();
    }

    public Authentication getAuthenticationFromAccessToken(String accessToken) {
        String tokenType = getTokenType(accessToken);
        if (!TokenType.ACCESS.name().equals(tokenType)) {
            throw new InvalidTokenException();
        }

        Claims claims = parseClaims(accessToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        return UsernamePasswordAuthenticationToken.authenticated(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public String getUsernameFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public boolean isValidateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTokenType(String token) {
        try {
            return (String) Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getHeader()
                    .get(tokenTypeHeaderKey);
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public RefreshToken createRefreshTokenEntity(String username, String refreshToken) {
        LocalDateTime now = dateTimeService.getLocalDateTimeNow();
        LocalDateTime expiredAt = now.plus(refreshTokenValidityInMilliseconds, ChronoUnit.MILLIS);
        return new RefreshToken(username, refreshToken, expiredAt);
    }
}
