package site.yacht.backend.domain.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.yacht.backend.domain.application.domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
