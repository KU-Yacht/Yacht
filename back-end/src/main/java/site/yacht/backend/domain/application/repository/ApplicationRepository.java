package site.yacht.backend.domain.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.yacht.backend.domain.application.domain.Application;
import site.yacht.backend.domain.project.domain.Project;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    boolean existsByNameAndProject(String name, Project project);

    List<Application> findByProjectId(Long projectId);

}
