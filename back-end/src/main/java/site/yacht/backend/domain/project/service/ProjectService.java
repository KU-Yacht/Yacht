package site.yacht.backend.domain.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.yacht.backend.domain.project.domain.Project;
import site.yacht.backend.domain.project.dto.UserProjectInfoDto;
import site.yacht.backend.domain.project.exception.ProjectNameDuplicationException;
import site.yacht.backend.domain.project.exception.ProjectNotFoundException;
import site.yacht.backend.domain.project.repository.ProjectRepository;
import site.yacht.backend.domain.user.domain.User;
import site.yacht.backend.domain.user.repository.UserRepository;
import site.yacht.backend.domain.user_project.domain.Role;
import site.yacht.backend.domain.user_project.domain.UserProject;
import site.yacht.backend.domain.user_project.repository.UserProjectRepository;
import site.yacht.backend.global.error.exception.AuthorizationException;
import site.yacht.backend.global.error.exception.IllegalException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final UserProjectRepository userProjectRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public List<UserProjectInfoDto> findProjects(User user) {
        List<UserProject> userProjects = userProjectRepository.findByUserJoinProject(user);
        return userProjects.stream()
                .map(userProject -> new UserProjectInfoDto(userProject.getProject().getId(), userProject.getProject().getName(), userProject.getRole()))
                .toList();
    }

    @Transactional
    public void inviteProject(Long userId, String projectName, List<String> emails) {
        Project project = projectRepository.findProjectByName(projectName)
                .orElseThrow(ProjectNotFoundException::new);

        validateInviterPermission(userId, project.getId());

        List<User> users = userRepository.findByEmailIn(emails);
        validateUser(project.getId(), emails, users);

        for (User user : users) {
            UserProject userProject = new UserProject(user, project, Role.ROLE_VIEWER);
            userProjectRepository.save(userProject);
        }
    }

    private void validateInviterPermission(Long userId, Long projectId) {
        UserProject userProject = userProjectRepository.findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(AuthorizationException::new);
        if (userProject.hasNotInvitationPermission()) {
            throw new AuthorizationException();
        }
    }

    private void validateUser(Long projectId, List<String> emails, List<User> users) {
        if (users.size() != emails.size()) {
            throw new IllegalException("Contains a user that doesn't exist");
        }

        for (User user : users) {
            if (userProjectRepository.existsByUserIdAndProjectId(user.getId(), projectId)) {
                throw new IllegalException("Contains users who are already joined");
            }
        }
    }

    @Transactional
    public void createProject(User user, String projectName) {
        if (projectRepository.existsByName(projectName)) {
            throw new ProjectNameDuplicationException();
        }

        Project project = new Project(projectName);
        projectRepository.save(project);

        UserProject userProject = new UserProject(user, project, Role.ROLE_ADMIN);
        userProjectRepository.save(userProject);
    }
}
