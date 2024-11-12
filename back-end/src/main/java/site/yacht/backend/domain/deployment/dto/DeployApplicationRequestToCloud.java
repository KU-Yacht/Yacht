package site.yacht.backend.domain.deployment.dto;

import site.yacht.backend.domain.application.domain.Application;

public record DeployApplicationRequestToCloud(Long applicationId,
                                              String region,

                                              String repoUrl,
                                              String path,
                                              String branch,
                                              String namespace,
                                              String image,
                                              int port,
                                              String appName,

                                              int replicas,
                                              double cpu,
                                              int memory) {
    public DeployApplicationRequestToCloud(Application application) {
        this(application.getId(),
                application.getRegion().name(),
                application.getGitUrl(),
                application.getPath(),
                application.getBranch(),
                application.getNamespace(),
                application.getImage(),
                application.getPort(),
                application.getName(),
                application.getReplicaNumber(),
                application.getCpu(),
                application.getMemory());
    }
}
