package site.yacht.backend.domain.project.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.yacht.backend.domain.project.api.request.CreateProjectRequest;
import site.yacht.backend.domain.project.api.request.InviteProjectRequest;
import site.yacht.backend.domain.project.api.response.FindProjectListResponse;
import site.yacht.backend.domain.project.dto.UserProjectInfoDto;
import site.yacht.backend.domain.project.service.ProjectService;
import site.yacht.backend.global.security.UserDetailsImpl;

import java.util.List;

@RestController
@RequestMapping("api/projects")
@RequiredArgsConstructor
@Tag(name = "Project", description = "프로젝트 API")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @Operation(summary = "자신의 프로젝트 조회", description = "현재 자신이 속한 프로젝트 목록을 조회합니다.")
    public List<FindProjectListResponse> findMyProjects(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<UserProjectInfoDto> userProjectInfoDtos = projectService.findProjects(userDetails.user());
        return userProjectInfoDtos.stream()
                .map(FindProjectListResponse::from)
                .toList();
    }

    @PostMapping
    @Operation(summary = "프로젝트 만들기", description = "프로젝트에 생성합니다. 생성한 사람은 ADMIN 권한을 가집니다.")
    public void createProject(@RequestBody @Valid CreateProjectRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        projectService.createProject(userDetails.user(), request.getProjectName());
    }

    @PostMapping("{projectName}/invitation")
    @Operation(summary = "프로젝트 초대", description = "프로젝트에 사용자를 초대합니다. 초대한 사람은 프로젝트의 ADMIN 권한을 가져야 합니다. 초대된 유저는 VIEWER 권한을 가집니다.")
    public void inviteProject(@PathVariable String projectName, @RequestBody @Valid InviteProjectRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("projectName: " + projectName);
        projectService.inviteProject(userDetails.user().getId(), projectName, request.getEmails());
    }
}
