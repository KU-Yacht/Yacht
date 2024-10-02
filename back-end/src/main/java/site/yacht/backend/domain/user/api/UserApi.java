package site.yacht.backend.domain.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import site.yacht.backend.domain.authentication.dto.TokenDto;
import site.yacht.backend.domain.user.api.request.UserLoginRequest;
import site.yacht.backend.domain.user.api.request.UserRegisterRequest;
import site.yacht.backend.domain.user.api.response.UserLoginResponse;
import site.yacht.backend.domain.user.service.UserLoginService;
import site.yacht.backend.domain.user.service.UserRegisterService;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 API")
public class UserApi {

    private final UserRegisterService userRegisterService;
    private final UserLoginService userLoginService;

    @PostMapping("register")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "회원가입", description = "신규 회원가입 합니다.")
    public void registerUser(@RequestBody @Valid UserRegisterRequest request) {
        userRegisterService.registerUser(request.getEmail(), request.getPassword(), request.getNickname());
    }

    @PostMapping("login")
    @Operation(summary = "로그인", description = "로그인")
    public UserLoginResponse loginUser(@RequestBody @Valid UserLoginRequest request) {
        TokenDto tokenDto = userLoginService.login(request.getEmail(), request.getPassword());
        return UserLoginResponse.from(tokenDto);
    }

}
