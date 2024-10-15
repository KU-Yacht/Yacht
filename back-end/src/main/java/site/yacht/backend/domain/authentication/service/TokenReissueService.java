package site.yacht.backend.domain.authentication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.yacht.backend.domain.authentication.domain.RefreshToken;
import site.yacht.backend.domain.authentication.dto.TokenDto;
import site.yacht.backend.domain.authentication.exception.InvalidTokenException;
import site.yacht.backend.domain.authentication.repository.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class TokenReissueService {

    private final TokenManager tokenManager;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public TokenDto reissue(String refreshToken) {
        String userEmail = tokenManager.getUsernameFromToken(refreshToken);
        if (!refreshTokenRepository.existsByUserEmail(userEmail)) {
            throw new InvalidTokenException();
        }

        TokenDto tokenDto = tokenManager.createTokenDto(userEmail);
        RefreshToken refreshTokenEntity = tokenManager.createRefreshTokenEntity(userEmail, tokenDto.refreshToken(), tokenDto.refreshTokenExpiredAt());
        refreshTokenRepository.save(refreshTokenEntity);

        return tokenDto;
    }
}
