package site.yacht.backend.domain.deployment_history.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import site.yacht.backend.domain.deployment_history.api.request.DeploymentHistorySaveRequest;
import site.yacht.backend.domain.deployment_history.dto.DeploymentHistorySaveDto;
import site.yacht.backend.domain.deployment_history.service.DeploymentHistoryService;

@RestController
@RequestMapping("api/histories")
@RequiredArgsConstructor
@Tag(name = "Deployment History", description = "배포 기록 API")
public class DeploymentHistoryApi {

    private final DeploymentHistoryService deploymentHistoryService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "배포 기록 저장", description = "배포 기록을 저장합니다.")
    public void saveDeploymentHistory(@RequestBody @Valid DeploymentHistorySaveRequest request) {
        DeploymentHistorySaveDto deploymentHistorySaveDto = new DeploymentHistorySaveDto(
                request.getApplicationId(),
                request.getGeneratedYaml(),
                request.getCommitId(),
                request.getArgoWorkflowId()
        );

        deploymentHistoryService.saveDeploymentHistory(deploymentHistorySaveDto);
    }

}
