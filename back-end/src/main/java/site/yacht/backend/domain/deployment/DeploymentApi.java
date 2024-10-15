package site.yacht.backend.domain.deployment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.yacht.backend.domain.application.domain.Application;
import site.yacht.backend.domain.application.exception.ApplicationNotFoundException;
import site.yacht.backend.domain.application.repository.ApplicationRepository;

@RestController
@RequestMapping("api/deployment")
@RequiredArgsConstructor
@Tag(name = "Deploy", description = "배포를 하자")
public class DeploymentApi {

    private final ApplicationRepository applicationRepository;

    @GetMapping("/{applicationId}")
    @Operation(summary = "애플리케이션 배포 정보 조회", description = "애플리케이션의 배포 정보를 조회합니다.")
    @Transactional
    public DeployDetailResponse findApplicationDetail(@PathVariable Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(ApplicationNotFoundException::new);

        return new DeployDetailResponse(application);
    }
}
