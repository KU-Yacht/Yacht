package site.yacht.backend.domain.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.yacht.backend.domain.application.api.response.FindApplicationDetailResponse;
import site.yacht.backend.domain.application.domain.Application;
import site.yacht.backend.domain.application.dto.ApplicationRegisterDto;
import site.yacht.backend.domain.application.exception.ApplicationNameDuplicationException;
import site.yacht.backend.domain.application.exception.ApplicationNotFoundException;
import site.yacht.backend.domain.application.repository.ApplicationRepository;
import site.yacht.backend.domain.deployment_history.domain.DeploymentHistory;
import site.yacht.backend.domain.deployment_history.repository.DeploymentHistoryRepository;
import site.yacht.backend.domain.project.domain.Project;
import site.yacht.backend.domain.project.exception.ProjectNotFoundException;
import site.yacht.backend.domain.project.repository.ProjectRepository;
import site.yacht.backend.domain.template.domain.Template;
import site.yacht.backend.domain.template.exception.TemplateNotFoundException;
import site.yacht.backend.domain.template.repository.TemplateRepository;
import site.yacht.backend.domain.user_project.domain.UserProject;
import site.yacht.backend.domain.user_project.repository.UserProjectRepository;
import site.yacht.backend.global.error.exception.AuthorizationException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final TemplateRepository templateRepository;
    private final ProjectRepository projectRepository;
    private final UserProjectRepository userProjectRepository;
    private final DeploymentHistoryRepository deploymentHistoryRepository;

    @Transactional
    public void registerApplication(ApplicationRegisterDto applicationRegisterDto) {
        Project project = projectRepository.findProjectByName(applicationRegisterDto.projectName())
                .orElseThrow(ProjectNotFoundException::new);
        validatePermission(applicationRegisterDto.user().getId(), project.getId());
        validateApplicationName(applicationRegisterDto.name(), project);

        Template template = templateRepository.findByTitle(applicationRegisterDto.templateName())
                .orElseThrow(TemplateNotFoundException::new);

        // GitUrl 검증
        // Docker file 검증
        String valueYaml = ""; // yaml 생성

        Application application = Application.builder()
                .project(project)
                .template(template)
                .valueYaml(valueYaml)
                .user(applicationRegisterDto.user())
                .name(applicationRegisterDto.name())
                .description(applicationRegisterDto.description())
                .gitUrl(applicationRegisterDto.gitUrl())
                .region(applicationRegisterDto.region())
                .namespace(applicationRegisterDto.namespace())
                .replicaNumber(applicationRegisterDto.replicaNumber())
                .cpu(applicationRegisterDto.cpu())
                .port(applicationRegisterDto.port())
                .memory(applicationRegisterDto.memory())
                .path(applicationRegisterDto.path())
                .image(applicationRegisterDto.image())
                .branch(applicationRegisterDto.branch())
                .build();

        applicationRepository.save(application);
    }

    private void validatePermission(Long userId, Long projectId) {
        UserProject userProject = userProjectRepository.findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(AuthorizationException::new);
        if (userProject.hasNotRegisterApplicationPermission()) {
            throw new AuthorizationException();
        }
    }

    private void validateApplicationName(String applicationName, Project project) {
        if (applicationRepository.existsByNameAndProject(applicationName, project)) {
            throw new ApplicationNameDuplicationException();
        }
    }

    public FindApplicationDetailResponse findApplicationDetail(Long userId, Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(ApplicationNotFoundException::new);
        if (!userProjectRepository.existsByUserIdAndProjectId(userId, application.getProject().getId())) {
            throw new AuthorizationException();
        }

        DeploymentHistory deploymentHistory = deploymentHistoryRepository.findTopByApplicationOrderByCreatedAtDesc(application)
                .orElse(null);

        return new FindApplicationDetailResponse(application, deploymentHistory);
    }
}

