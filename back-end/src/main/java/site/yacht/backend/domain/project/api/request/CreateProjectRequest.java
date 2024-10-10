package site.yacht.backend.domain.project.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class CreateProjectRequest {

    @NotBlank(message = "프로젝트 이름은 필수값입니다.")
    @Length(min = 2, max = 20, message = "프로젝트 이름은 2글자 이상 20글자 이하여야 합니다.")
    @Pattern(regexp = "^[0-9a-zA-Z가-힣]*$", message = "프로젝트 이름은 한글, 영어, 숫자만 허용됩니다.")
    @Schema(description = "사용할 프로젝트 이름(한글, 숫자, 영어 사용가능)", example = "myProject", minLength = 2, maxLength = 20, requiredMode = RequiredMode.REQUIRED)
    private String projectName;

}
