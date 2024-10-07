package site.yacht.backend.domain.application.dto;

import lombok.Builder;
import site.yacht.backend.domain.application.domain.Region;
import site.yacht.backend.domain.user.domain.User;

@Builder
public record ApplicationRegisterDto(User user,
                                     String name,
                                     String description,
                                     String gitUrl,
                                     Region region,
                                     String namespace,
                                     int replicaNumber,
                                     int cpu,
                                     int memory,
                                     int port,
                                     String templateName,
                                     String projectName) {
}
