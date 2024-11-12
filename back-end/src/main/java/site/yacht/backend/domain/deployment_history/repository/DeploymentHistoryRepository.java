package site.yacht.backend.domain.deployment_history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.yacht.backend.domain.application.domain.Application;
import site.yacht.backend.domain.deployment_history.domain.DeploymentHistory;

import java.util.Optional;

public interface DeploymentHistoryRepository extends JpaRepository<DeploymentHistory, Long> {
    Optional<DeploymentHistory> findTopByApplicationOrderByCreatedAtDesc(Application application);
}
