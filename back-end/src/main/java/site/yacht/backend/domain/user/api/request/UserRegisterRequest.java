package site.yacht.backend.domain.user.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UserRegisterRequest {

    @NotBlank(message = "이메일은 필수값입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @Schema(description = "이메일", example = "tester@naver.com", requiredMode = RequiredMode.REQUIRED)
    private String email;

    @NotBlank(message = "닉네임은 필수값입니다.")
    @Length(min = 2, max = 8, message = "닉네임은 2글자 이상 8글자 이하여야 합니다.")
    @Pattern(regexp = "^[0-9a-zA-Z가-힣]*$", message = "닉네임은 한글, 영어, 숫자만 허용됩니다.")
    @Schema(description = "사용할 닉네임(한글, 영어, 숫자만 허용)", example = "tester", minLength = 2, maxLength = 8, requiredMode = RequiredMode.REQUIRED)
    private String nickname;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4글자 이상 16글자 이하여야 합니다.")
    @Schema(description = "비밀번호", example = "q1w2e3r4", minLength = 4, maxLength = 16, requiredMode = RequiredMode.REQUIRED)
    private String password;

}
