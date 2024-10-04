package site.yacht.backend.domain.project.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.yacht.backend.domain.project.dto.UserProjectInfoDto;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class FindProjectListResponse {

    @Schema(description = "프로젝트 id", example = "3", requiredMode = RequiredMode.REQUIRED)
    private long projectId;

    @Schema(description = "프로젝트 이름", example = "myProject", requiredMode = RequiredMode.REQUIRED)
    private String projectName;

    @Schema(description = "해당 프로젝트에서 자신의 role(ROLE_ADMIN, ROLE_EDITOR, ROLE_VIEWER)", example = "ROLE_ADMIN", requiredMode = RequiredMode.REQUIRED)
    private String role;

    public static FindProjectListResponse from(UserProjectInfoDto userProjectInfoDto) {
        return new FindProjectListResponse(
                userProjectInfoDto.projectId(),
                userProjectInfoDto.projectName(),
                userProjectInfoDto.role().name()
        );
    }
}
