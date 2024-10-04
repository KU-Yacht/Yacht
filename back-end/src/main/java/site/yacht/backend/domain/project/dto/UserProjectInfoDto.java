package site.yacht.backend.domain.project.dto;

import site.yacht.backend.domain.user_project.domain.Role;

public record UserProjectInfoDto(long projectId, String projectName, Role role) {
}
