package site.yacht.backend.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import site.yacht.backend.domain.authentication.domain.GrantType;

@OpenAPIDefinition(
        info = @Info(
                title = "Yacht API 명세서",
                version = "v0.0.1"
        )
)
@Configuration
@Profile("!prod")
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        String jwtSchemeName = "Authorization";

        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList(jwtSchemeName);

        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.name(jwtSchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme(GrantType.BEARER.getType());

        Components components = new Components();
        components.addSecuritySchemes(jwtSchemeName, securityScheme);

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components);
    }

}