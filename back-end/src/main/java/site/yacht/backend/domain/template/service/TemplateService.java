package site.yacht.backend.domain.template.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.yacht.backend.domain.template.domain.Template;
import site.yacht.backend.domain.template.repository.TemplateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;

    public List<Template> findTemplates() {
        return templateRepository.findAll();
    }
}
