package site.yacht.backend.domain.deployment_history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.yacht.backend.domain.deployment_history.domain.DeploymentHistory;

public interface DeploymentHistoryRepository extends JpaRepository<DeploymentHistory, Long> {

}
