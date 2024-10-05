package site.yacht.backend.domain.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.yacht.backend.domain.project.domain.Project;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsByName(String projectName);

    Optional<Project> findProjectByName(String projectName);
}
