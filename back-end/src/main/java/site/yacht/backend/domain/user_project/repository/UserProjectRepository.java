package site.yacht.backend.domain.user_project.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.yacht.backend.domain.user.domain.User;
import site.yacht.backend.domain.user_project.domain.UserProject;

import java.util.List;
import java.util.Optional;

public interface UserProjectRepository extends JpaRepository<UserProject, Long> {

    @EntityGraph(attributePaths = {"project"})
    @Query("select up from UserProject up where up.user = :user")
    List<UserProject> findByUserJoinProject(User user);

    Optional<UserProject> findByUserIdAndProjectId(Long userId, Long projectId);

    boolean existsByUserIdAndProjectId(Long userId, Long projectId);

}
