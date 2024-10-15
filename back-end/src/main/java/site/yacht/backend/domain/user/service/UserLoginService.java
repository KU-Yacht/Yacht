package site.yacht.backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.yacht.backend.domain.authentication.domain.RefreshToken;
import site.yacht.backend.domain.authentication.dto.TokenDto;
import site.yacht.backend.domain.authentication.repository.RefreshTokenRepository;
import site.yacht.backend.domain.authentication.service.TokenManager;
import site.yacht.backend.domain.user.domain.User;
import site.yacht.backend.domain.user.dto.LoginInformation;
import site.yacht.backend.domain.user.repository.UserRepository;
import site.yacht.backend.global.error.exception.AuthenticationException;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenManager tokenManager;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public LoginInformation login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(AuthenticationException::new);

        String encryptedPassword = user.getPassword();
        if (!passwordEncoder.matches(password, encryptedPassword)) {
            throw new AuthenticationException();
        }

        TokenDto tokenDto = tokenManager.createTokenDto(user.getEmail());

        saveUniqueRefreshToken(user, tokenDto);

        return new LoginInformation(tokenDto, user);
    }

    private void saveUniqueRefreshToken(User user, TokenDto tokenDto) {
        refreshTokenRepository.deleteByUserEmail(user.getEmail());

        RefreshToken refreshTokenEntity = tokenManager.createRefreshTokenEntity(
                user.getEmail(),
                tokenDto.refreshToken(),
                tokenDto.refreshTokenExpiredAt()
        );
        refreshTokenRepository.save(refreshTokenEntity);
    }

}
