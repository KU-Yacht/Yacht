package site.yacht.backend.domain.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.yacht.backend.domain.template.domain.Template;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
