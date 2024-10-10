package site.yacht.backend.domain.deployment_history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.yacht.backend.domain.application.domain.Application;
import site.yacht.backend.domain.application.exception.ApplicationNotFoundException;
import site.yacht.backend.domain.application.repository.ApplicationRepository;
import site.yacht.backend.domain.deployment_history.domain.DeploymentHistory;
import site.yacht.backend.domain.deployment_history.dto.DeploymentHistorySaveDto;
import site.yacht.backend.domain.deployment_history.repository.DeploymentHistoryRepository;

@Service
@RequiredArgsConstructor
public class DeploymentHistoryService {

    private final ApplicationRepository applicationRepository;
    private final DeploymentHistoryRepository deploymentHistoryRepository;

    @Transactional
    public void saveDeploymentHistory(DeploymentHistorySaveDto deploymentHistorySaveDto) {
        Application application = applicationRepository.findById(deploymentHistorySaveDto.applicationId())
                .orElseThrow(ApplicationNotFoundException::new);

        DeploymentHistory deploymentHistory = DeploymentHistory.builder()
                .application(application)
                .generatedYaml(deploymentHistorySaveDto.generatedYaml())
                .commitId(deploymentHistorySaveDto.commitId())
                .argoWorkflowId(deploymentHistorySaveDto.argoWorkflowId())
                .build();

        deploymentHistoryRepository.save(deploymentHistory);
    }
}
