package site.yacht.backend.domain.application.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.yacht.backend.domain.application.api.request.ApplicationRegisterRequest;
import site.yacht.backend.domain.application.api.response.FindApplicationDetailResponse;
import site.yacht.backend.domain.application.domain.Region;
import site.yacht.backend.domain.application.dto.ApplicationRegisterDto;
import site.yacht.backend.domain.application.service.ApplicationService;
import site.yacht.backend.global.error.exception.IllegalException;
import site.yacht.backend.global.security.UserDetailsImpl;

@RestController
@RequestMapping("api/applications")
@RequiredArgsConstructor
@Tag(name = "Application", description = "애플리케이션 API")
public class ApplicationApi {

    private final ApplicationService applicationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "애플리케이션 등록", description = "신규 애플리케이션을 등록합니다.")
    public void registerApplication(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                    @RequestBody @Valid ApplicationRegisterRequest request) {
        Region region = Region.fromString(request.getRegion())
                .orElseThrow(() -> new IllegalException("wrong region"));

        ApplicationRegisterDto applicationRegisterDto = ApplicationRegisterDto.builder()
                .region(region)
                .cpu(request.getCpu())
                .port(request.getPort())
                .user(userDetails.user())
                .memory(request.getMemory())
                .gitUrl(request.getGitUrl())
                .namespace(request.getNamespace())
                .name(request.getApplicationName())
                .description(request.getDescription())
                .projectName(request.getProjectName())
                .templateName(request.getTemplateName())
                .replicaNumber(request.getReplicaNumber())
                .build();

        applicationService.registerApplication(applicationRegisterDto);
    }

    @GetMapping("/{applicationId}")
    @Operation(summary = "애플리케이션 상세 정보 조회", description = "애플리케이션의 상세 정보를 조회합니다. 조회하는 사용자는 애플리케이션의 프로젝트에 속해있어야 합니다.")
    public FindApplicationDetailResponse findApplicationDetail(@PathVariable Long applicationId,
                                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return applicationService.findApplicationDetail(userDetails.user().getId(), applicationId);
    }

}
