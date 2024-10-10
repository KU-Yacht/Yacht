package site.yacht.backend.domain.authentication.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import site.yacht.backend.domain.authentication.dto.TokenDto;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TokenReissueResponse {

    @Schema(description = "Bearer 고정", example = "Bearer", requiredMode = Schema.RequiredMode.REQUIRED)
    private String grantType;

    @Schema(description = "새로 발급된 Access Token", example = "eyJ0eXAiOiJ..{생략}..", requiredMode = Schema.RequiredMode.REQUIRED)
    private String accessToken;

    @Schema(description = "새로 발급된 Refresh Token", example = "eyJ0aAdJ13..{생략}..", requiredMode = Schema.RequiredMode.REQUIRED)
    private String refreshToken;

    public static TokenReissueResponse from(TokenDto tokenDto) {
        return new TokenReissueResponse(tokenDto.grantType(), tokenDto.accessToken(), tokenDto.refreshToken());
    }
}
