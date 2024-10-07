package site.yacht.backend.domain.application.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ApplicationRegisterRequest {

    @NotBlank(message = "프로젝트 이름은 필수값입니다.")
    @Schema(description = "애플리케이션을 생성시킬 프로젝트 이름. 해당 프로젝트에서 EDITOR 이상의 권한이 필요합니다.", example = "myProject", requiredMode = RequiredMode.REQUIRED)
    private String projectName;

    @NotBlank(message = "애플리케이션 이름은 필수값입니다.")
    @Length(min = 2, max = 16, message = "애플리케이션 이름은 2글자 이상 16글자 이하여야 합니다.")
    @Schema(description = "애플리케이션 프로젝트 이름", example = "myApplication", minLength = 2, maxLength = 16, requiredMode = RequiredMode.REQUIRED)
    private String applicationName;

    @Length(max = 100, message = "애플리케이션 설명은 100글자 이하여야 합니다.")
    @Schema(description = "애플리케이션 설명", example = "This is my first application", maxLength = 100)
    private String description;

    @NotBlank(message = "git URL은 필수값입니다.")
    @Schema(description = "GIT URL", example = "https://github.com/KU-Yacht/Yacht", requiredMode = RequiredMode.REQUIRED)
    private String gitUrl;

    @NotBlank(message = "value Yaml은 필수값입니다.")
    @Schema(description = "value Yaml", example = "", requiredMode = RequiredMode.REQUIRED)
    private String valueYaml;

    @Schema(description = "template 이름", example = "Simple spring server")
    private String templateName;

}
