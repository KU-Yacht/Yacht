package site.yacht.backend.domain.template.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.yacht.backend.domain.template.api.response.FindTemplatesResponse;
import site.yacht.backend.domain.template.domain.Template;
import site.yacht.backend.domain.template.service.TemplateService;

import java.util.List;

@RestController
@RequestMapping("api/templates")
@RequiredArgsConstructor
@Tag(name = "Template", description = "템플릿 API")
public class TemplateController {

    private final TemplateService templateService;

    @GetMapping
    @Operation(summary = "템플릿 리스트 조회", description = "현재 사용 가능한 템플릿 리스트를 조회합니다.")
    public List<FindTemplatesResponse> findTemplates() {
        List<Template> templates = templateService.findTemplates();
        return templates.stream()
                .map(FindTemplatesResponse::from)
                .toList();
    }

}
