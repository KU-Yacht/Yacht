package site.yacht.backend.domain.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.yacht.backend.domain.project.domain.Project;
import site.yacht.backend.domain.project.dto.UserProjectInfoDto;
import site.yacht.backend.domain.project.repository.ProjectRepository;
import site.yacht.backend.domain.user.domain.User;
import site.yacht.backend.domain.user.repository.UserRepository;
import site.yacht.backend.domain.user_project.domain.Role;
import site.yacht.backend.domain.user_project.domain.UserProject;
import site.yacht.backend.domain.user_project.repository.UserProjectRepository;

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
    public void inviteProject(Long userId, Long projectId, List<String> emails) {
        Project project = projectRepository.findProjectById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));

        validateInviterPermission(userId, projectId);

        List<User> users = userRepository.findByEmailIn(emails);
        validateUser(projectId, emails, users);

        for (User user : users) {
            UserProject userProject = new UserProject(user, project, Role.ROLE_VIEWER);
            userProjectRepository.save(userProject);
        }
    }

    private void validateInviterPermission(Long userId, Long projectId) {
        UserProject userProject = userProjectRepository.findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트에 참여하고 있지 않습니다."));
        if (userProject.hasNotInvitationPermission()) {
            throw new IllegalArgumentException("해당 프로젝트에 초대 권한이 없습니다.");
        }
    }

    private void validateUser(Long projectId, List<String> emails, List<User> users) {
        if (users.size() != emails.size()) {
            throw new IllegalArgumentException("존재하지 않는 사용자가 포함되어 있습니다.");
        }

        for (User user : users) {
            if (userProjectRepository.existsByUserIdAndProjectId(user.getId(), projectId)) {
                throw new IllegalArgumentException("이미 참여하고 있는 사용자가 포함되어 있습니다.");
            }
        }
    }

    @Transactional
    public void createProject(User user, String projectName) {
        if (projectRepository.existsByName(projectName)) {
            throw new IllegalArgumentException("이미 존재하는 프로젝트 이름입니다.");
        }

        Project project = new Project(projectName);
        projectRepository.save(project);

        UserProject userProject = new UserProject(user, project, Role.ROLE_ADMIN);
        userProjectRepository.save(userProject);
    }
}
