package site.yacht.backend.domain.user.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UserLoginRequest {

    @NotBlank(message = "이메일은 필수값입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @Schema(description = "이메일", example = "tester@naver.com", requiredMode = RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4글자 이상 16글자 이하여야 합니다.")
    @Schema(description = "비밀번호", example = "q1w2e3r4", minLength = 4, maxLength = 16, requiredMode = RequiredMode.REQUIRED)
    private String password;

}
