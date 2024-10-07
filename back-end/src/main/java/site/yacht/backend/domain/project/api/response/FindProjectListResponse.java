package site.yacht.backend.domain.project.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import site.yacht.backend.domain.project.dto.UserProjectInfoDto;

import java.util.List;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class FindProjectListResponse {

    @Schema(description = "프로젝트 id", example = "3", requiredMode = RequiredMode.REQUIRED)
    private long projectId;

    @Schema(description = "프로젝트 이름", example = "myProject", requiredMode = RequiredMode.REQUIRED)
    private String projectName;

    @Schema(description = "해당 프로젝트에서 자신의 role(ROLE_ADMIN, ROLE_EDITOR, ROLE_VIEWER)", example = "ROLE_ADMIN", requiredMode = RequiredMode.REQUIRED)
    private String role;

    @Schema(description = "애플리케이션 리스트", requiredMode = RequiredMode.REQUIRED)
    private List<ApplicationInfo> applications;

    public static FindProjectListResponse from(UserProjectInfoDto userProjectInfoDto) {

        List<ApplicationInfo> applicationInfoList = userProjectInfoDto.applications().stream()
                .map(application -> new ApplicationInfo(application.getId(), application.getName()))
                .toList();

        return new FindProjectListResponse(
                userProjectInfoDto.projectId(),
                userProjectInfoDto.projectName(),
                userProjectInfoDto.role().name(),
                applicationInfoList
        );
    }

    @Getter
    @AllArgsConstructor
    public static class ApplicationInfo {

        @Schema(description = "애플리케이션 id", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
        private long id;

        @Schema(description = "애플리케이션 이름", example = "myApplication", requiredMode = Schema.RequiredMode.REQUIRED)
        private String name;
    }
}
