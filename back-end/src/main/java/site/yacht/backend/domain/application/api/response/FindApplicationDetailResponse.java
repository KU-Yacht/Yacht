package site.yacht.backend.domain.application.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.yacht.backend.domain.application.domain.Application;
import site.yacht.backend.domain.deployment_history.domain.DeploymentHistory;

import java.time.LocalDateTime;

@Getter
public class FindApplicationDetailResponse {

    private UserInfo userInfo;
    private TemplateInfo templateInfo;
    private LatestDeployment latestDeployment;

    private String region;
    private String name;
    private String description;
    private String gitUrl;
    private String namespace;
    private int replicaNumber;
    private int cpu;
    private int memory;
    private int port;

    @Getter
    @AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static class UserInfo {
        private long userId;
        private String userNickname;
    }

    @Getter
    @AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static class TemplateInfo {
        private long templateId;
        private String templateName;
    }

    @Getter
    @AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
    public static class LatestDeployment {
        private String commitId;
        private String argoWorkflowId;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh-mm-ss", timezone = "Asia/Seoul")
        @Schema(description = "배포 완료 시각(yyyy-MM-dd hh-mm-ss)", example = "2024-10-09 15:45:11", requiredMode = Schema.RequiredMode.REQUIRED)
        private LocalDateTime completeAt;
    }

    public FindApplicationDetailResponse(Application application, DeploymentHistory deploymentHistory) {
        this.userInfo = new UserInfo(application.getUser().getId(), application.getUser().getNickname());
        this.templateInfo = new TemplateInfo(application.getTemplate().getId(), application.getTemplate().getTitle());

        this.region = application.getRegion().name();
        this.name = application.getName();
        this.description = application.getDescription();
        this.gitUrl = application.getGitUrl();
        this.namespace = application.getNamespace();
        this.replicaNumber = application.getReplicaNumber();
        this.cpu = application.getCpu();
        this.memory = application.getMemory();
        this.port = application.getPort();

        if (deploymentHistory != null) {
            this.latestDeployment = new LatestDeployment(deploymentHistory.getCommitId(), deploymentHistory.getArgoWorkflowId(), deploymentHistory.getCreatedAt());
        }
    }
}
