package site.yacht.backend.domain.authentication.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.yacht.backend.domain.authentication.api.request.TokenReissueRequest;
import site.yacht.backend.domain.authentication.api.response.TokenReissueResponse;
import site.yacht.backend.domain.authentication.dto.TokenDto;
import site.yacht.backend.domain.authentication.service.TokenReissueService;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증, 토큰 서비스")
public class TokenApi {

    private final TokenReissueService tokenReissueService;

    @PostMapping("reissue")
    @Operation(summary = "access token 재발급", description = "access token과 refresh token을 재발급받습니다.")
    public TokenReissueResponse accessTokenReissue(@RequestBody @Valid TokenReissueRequest request) {
        TokenDto tokenDto = tokenReissueService.reissue(request.getRefreshToken());
        return TokenReissueResponse.from(tokenDto);
    }

}
