package site.yacht.backend.domain.deployment.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.yacht.backend.domain.application.domain.Application;

@Getter
public class DeployDetailResponse {

    private TemplateInfo templateInfo;

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
    public static class TemplateInfo {
        private long templateId;
        private String templateName;
    }

    public DeployDetailResponse(Application application) {
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
    }
}
