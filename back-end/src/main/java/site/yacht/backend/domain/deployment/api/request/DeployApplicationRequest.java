package site.yacht.backend.domain.deployment.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DeployApplicationRequest {
    private String region;
    private String name;
    private String description;
    private String gitURL;
    private String namespace;
    private String imageName;
    private int templateID;
    private int replicas;
    private int cpu;
    private int memory;
    private int port;
}
