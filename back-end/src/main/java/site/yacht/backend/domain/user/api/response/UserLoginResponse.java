package site.yacht.backend.domain.user.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.yacht.backend.domain.authentication.dto.TokenDto;
import site.yacht.backend.domain.user.domain.User;
import site.yacht.backend.domain.user.dto.LoginInformation;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UserLoginResponse {

    @Schema(description = "Bearer 고정", example = "Bearer", requiredMode = RequiredMode.REQUIRED)
    private String grantType;

    @Schema(description = "Access Token", example = "eyJ0eXAiOiJ..{생략}..", requiredMode = RequiredMode.REQUIRED)
    private String accessToken;

    @Schema(description = "Refresh Token", example = "eyJ0aAdJ13..{생략}..", requiredMode = RequiredMode.REQUIRED)
    private String refreshToken;

    @Schema(description = "사용자 닉네임", example = "tester", requiredMode = RequiredMode.REQUIRED)
    private String nickname;

    @Schema(description = "사용자 이메일", example = "tester@naver.com", requiredMode = RequiredMode.REQUIRED)
    private String email;

    public static UserLoginResponse from(LoginInformation loginInformation) {
        TokenDto tokenDto = loginInformation.tokenDto();
        User user = loginInformation.user();
        return new UserLoginResponse(
                tokenDto.grantType(),
                tokenDto.accessToken(),
                tokenDto.refreshToken(),
                user.getNickname(),
                user.getEmail()
        );
    }
}
