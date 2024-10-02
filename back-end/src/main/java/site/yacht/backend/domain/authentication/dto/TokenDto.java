package site.yacht.backend.domain.authentication.dto;

import java.util.Date;

public record TokenDto(
        String grantType,
        String accessToken,
        String refreshToken,
        Date accessTokenExpiredAt,
        Date refreshTokenExpiredAt) {
}
