package site.yacht.backend.domain.application.dto;

import site.yacht.backend.domain.user.domain.User;

public record ApplicationRegisterDto(User user,
                                     String name,
                                     String description,
                                     String gitUrl,
                                     String valueYaml,
                                     String templateName,
                                     String projectName) {
}
