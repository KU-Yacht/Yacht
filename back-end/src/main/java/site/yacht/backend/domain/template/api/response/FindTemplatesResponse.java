package site.yacht.backend.domain.template.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.yacht.backend.domain.template.domain.Template;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class FindTemplatesResponse {

    @Schema(description = "템플릿 이름", example = "spring server template", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "템플릿 설명", example = "Simple spring server template", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    public static FindTemplatesResponse from(Template template) {
        return new FindTemplatesResponse(template.getTitle(), template.getDescription());
    }

}
