package site.yacht.backend.domain.project.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class InviteProjectRequest {

    @NotNull
    @Schema(description = "초대할 유저의 이메일 리스트(1개 이상)", example = "[\"user1@naver.com\", \"user2@naver.com\"]", type = "array", requiredMode = RequiredMode.REQUIRED)
    @Size(min = 1, message = "이메일은 최소 1개 이상 입력해야 합니다.")
    private List<@NotBlank @Email(message = "올바른 이메일 형식이 아닙니다.") String> emails;

}
