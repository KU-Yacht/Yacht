package site.yacht.backend.global.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import site.yacht.backend.domain.authentication.domain.GrantType;
import site.yacht.backend.domain.authentication.domain.TokenType;
import site.yacht.backend.domain.authentication.service.TokenManager;
import site.yacht.backend.global.error.ErrorCode;
import site.yacht.backend.global.error.ErrorResponse;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenManager tokenManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = resolveToken(request);
        if (accessToken != null) {
            if (isValidateAccessToken(accessToken)) {
                try {
                    Authentication authentication = tokenManager.getAuthenticationFromAccessToken(accessToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (Exception e) {
                    jwtExceptionHandler(response, ErrorCode.FAIL_AUTHENTICATION);
                    return;
                }
            }
            else {
                jwtExceptionHandler(response, ErrorCode.INVALID_TOKEN);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(bearerToken) || !bearerToken.startsWith(GrantType.BEARER.getType())) {
            return null;
        }
        return bearerToken.substring(GrantType.BEARER.getType().length() + 1);
    }

    private boolean isValidateAccessToken(String accessToken) {
        String tokenType = tokenManager.getTokenType(accessToken);
        return TokenType.ACCESS.name().equals(tokenType);
    }

    private void jwtExceptionHandler(HttpServletResponse response, ErrorCode errorCode) {
        response.setStatus(errorCode.getStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            ErrorResponse errorResponse = ErrorResponse.of(errorCode);
            String responseBody = objectMapper.writeValueAsString(errorResponse);
            response.getWriter().write(responseBody);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException", e);
        } catch (IOException e) {
            log.error("IOException during jwtException handling", e);
        }
    }
}
