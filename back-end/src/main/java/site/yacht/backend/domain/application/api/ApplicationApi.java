package site.yacht.backend.domain.application.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import site.yacht.backend.domain.application.api.request.ApplicationRegisterRequest;
import site.yacht.backend.domain.application.dto.ApplicationRegisterDto;
import site.yacht.backend.domain.application.service.ApplicationRegisterService;
import site.yacht.backend.global.security.UserDetailsImpl;

@RestController
@RequestMapping("api/applications")
@RequiredArgsConstructor
@Tag(name = "Application", description = "애플리케이션 API")
public class ApplicationApi {

    private final ApplicationRegisterService applicationRegisterService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "애플리케이션 등록", description = "신규 애플리케이션을 등록합니다.")
    public void registerApplication(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                    @RequestBody @Valid ApplicationRegisterRequest request) {

        ApplicationRegisterDto applicationRegisterDto = new ApplicationRegisterDto(
                userDetails.user(),
                request.getApplicationName(),
                request.getDescription(),
                request.getGitUrl(),
                request.getValueYaml(),
                request.getTemplateName(),
                request.getProjectName()
        );

        applicationRegisterService.registerApplication(applicationRegisterDto);
    }

}
