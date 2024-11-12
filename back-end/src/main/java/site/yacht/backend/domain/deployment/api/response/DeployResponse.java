package site.yacht.backend.domain.deployment.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class DeployResponse {

    @Schema(description = "배포 요청 성공여부", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isSuccess;

    public DeployResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
