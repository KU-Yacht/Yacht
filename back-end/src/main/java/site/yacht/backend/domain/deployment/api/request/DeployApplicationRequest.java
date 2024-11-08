package site.yacht.backend.domain.deployment.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DeployApplicationRequest {

    @Schema(description = "애플리케이션 ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long applicationId;
    @Schema(description = "배포할 지역(명목상 받음)", example = "KOREA", requiredMode = Schema.RequiredMode.REQUIRED)
    private String region;
    @Schema(description = "템플릿 이름", example = "Simple Spring", requiredMode = Schema.RequiredMode.REQUIRED)
    private String templateName;

    @Schema(description = "github URL", example = "https://github.com/KU-Yacht/Yacht", requiredMode = Schema.RequiredMode.REQUIRED)
    private String repoUrl;
    @Schema(description = "도커파일이 위치한 위치", example = ".", requiredMode = Schema.RequiredMode.REQUIRED)
    private String path;
    @Schema(description = "배포할 레포 브런치", example = "main", requiredMode = Schema.RequiredMode.REQUIRED)
    private String branch;
    @Schema(description = "namespace", example = "default", requiredMode = Schema.RequiredMode.REQUIRED)
    private String namespace;
    @Schema(description = "image", example = "yacht24/new-test:v0.0.1", requiredMode = Schema.RequiredMode.REQUIRED)
    private String image;
    @Schema(description = "포트번호", example = "8080", requiredMode = Schema.RequiredMode.REQUIRED)
    private int port;
    @Schema(description = "애플리케이션 이름", example = "myApplication", requiredMode = Schema.RequiredMode.REQUIRED)
    private String appName;

    @Schema(description = "레플리카 개수", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private int replicas;
    @Schema(description = "CPU 스팩", example = "0.1", minimum = "0.1", maximum = "1.0", requiredMode = Schema.RequiredMode.REQUIRED)
    private double cpu;
    @Schema(description = "메모리 스팩(단위: mi)", example = "500", requiredMode = Schema.RequiredMode.REQUIRED)
    private int memory;
}
