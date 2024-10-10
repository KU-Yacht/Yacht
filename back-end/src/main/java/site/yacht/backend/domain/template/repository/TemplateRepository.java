package site.yacht.backend.domain.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.yacht.backend.domain.template.domain.Template;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    Optional<Template> findByTitle(String title);

}
