package site.yacht.backend.domain.deployment.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import site.yacht.backend.domain.application.domain.Application;
import site.yacht.backend.domain.application.exception.ApplicationNotFoundException;
import site.yacht.backend.domain.application.repository.ApplicationRepository;
import site.yacht.backend.domain.deployment.api.response.DeployDetailResponse;
import site.yacht.backend.domain.deployment.service.DeployService;
import site.yacht.backend.global.security.UserDetailsImpl;

@RestController
@RequestMapping("api/deployment")
@RequiredArgsConstructor
@Tag(name = "Deploy", description = "배포를 하자")
public class DeploymentApi {

    private final ApplicationRepository applicationRepository;
    private final DeployService deployService;

    @GetMapping("/{applicationId}")
    @Operation(summary = "애플리케이션 배포 정보 조회", description = "애플리케이션의 배포 정보를 조회합니다.")
    @Transactional
    public DeployDetailResponse findDeploymentDetail(@PathVariable Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(ApplicationNotFoundException::new);

        return new DeployDetailResponse(application);
    }

    @PostMapping("/{applicationId}")
    @Operation(summary = "애플리케이션 배포하기", description = "애플리케이션을 배포합니다.")
    public String deployApplication(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                    @PathVariable Long applicationId) {
        boolean deploy = deployService.deploy(userDetails.user().getId(), applicationId);
        if (deploy) {
            return "{\"isSuccess\": true}";
        }
        else {
            return "{\"isSuccess\": false}";
        }
    }
}
