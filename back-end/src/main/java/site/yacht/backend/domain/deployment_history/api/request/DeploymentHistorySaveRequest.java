package site.yacht.backend.domain.deployment_history.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DeploymentHistorySaveRequest {

    @NotNull(message = "프로젝트 id는 필수값입니다.")
    @Schema(description = "applicationId", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Long applicationId;

    //@NotBlank(message = "generated yaml은 필수값입니다.")
    @Schema(description = "generated yaml", example = "", requiredMode = RequiredMode.REQUIRED)
    private String generatedYaml;

    //@NotBlank(message = "commitId은 필수값입니다.")
    @Schema(description = "배포 commitId(github)", example = "203d687b48760a309893b2c0239bf533f8d6e6be", requiredMode = RequiredMode.REQUIRED)
    private String commitId;

    //@NotBlank(message = "argo work flow id은 필수값입니다.")
    @Schema(description = "argo work flow id", example = "", requiredMode = RequiredMode.REQUIRED)
    private String argoWorkflowId;

}
