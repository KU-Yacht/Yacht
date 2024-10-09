package site.yacht.backend.domain.deployment_history.dto;

public record DeploymentHistorySaveDto(Long applicationId,
                                       String generatedYaml,
                                       String commitId,
                                       String argoWorkflowId) {
}
