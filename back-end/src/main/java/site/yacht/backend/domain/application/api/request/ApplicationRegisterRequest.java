package site.yacht.backend.domain.application.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import site.yacht.backend.domain.application.domain.Region;
import site.yacht.backend.global.validation.ValidEnum;

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

    @NotBlank(message = "template은 필수값입니다.")
    @Schema(description = "template 이름", example = "spring server template", requiredMode = RequiredMode.REQUIRED)
    private String templateName;

    @ValidEnum(enumClass = Region.class)
    @Schema(description = "Region(KOREA, US, JAPAN, CHINA)", example = "KOREA", requiredMode = RequiredMode.REQUIRED)
    private String region;

    @NotBlank(message = "namespace는 필수값입니다.")
    @Schema(description = "namespace", example = "default", requiredMode = RequiredMode.REQUIRED)
    private String namespace;

    @Range(min = 1, max = 10, message = "replica number는 1이상 10이하여야 합니다.")
    @Schema(description = "replicaNumber", example = "1", minimum = "1", maximum = "10", requiredMode = RequiredMode.REQUIRED)
    private int replicaNumber;

    @Schema(description = "cpu", example = "0.1", minimum = "0.1", maximum = "1.0", requiredMode = RequiredMode.REQUIRED)
    private double cpu;

    @Range(min = 256, max = 164064, message = "memory는 256이상 164064이하여야 합니다.")
    @Schema(description = "memory(mi)", example = "2048", minimum = "256", maximum = "164064", requiredMode = RequiredMode.REQUIRED)
    private int memory;

    @Range(min = 1, max = 65535, message = "port는 1이상 65535이하여야 합니다.")
    @Schema(description = "port", example = "8080", minimum = "1", maximum = "65535", requiredMode = RequiredMode.REQUIRED)
    private int port;

    @NotBlank(message = "path은 필수값입니다.")
    @Schema(description = "도커파일이 위치한 위치", example = ".", requiredMode = Schema.RequiredMode.REQUIRED)
    private String path;

    @NotBlank(message = "branch은 필수값입니다.")
    @Schema(description = "배포할 레포 브런치", example = "main", requiredMode = Schema.RequiredMode.REQUIRED)
    private String branch;

    @NotBlank(message = "image는 필수값입니다.")
    @Schema(description = "image", example = "yacht24/new-test:v0.0.1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String image;
}
