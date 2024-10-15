package site.yacht.backend.domain.project.dto;

import site.yacht.backend.domain.application.domain.Application;
import site.yacht.backend.domain.user_project.domain.Role;

import java.util.List;

public record UserProjectInfoDto(long projectId, String projectName, Role role, List<Application> applications) {
}
