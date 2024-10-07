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

import java.util.List;
import java.util.stream.Collectors;

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
    public OpenAPI openAPI(BearerTokenProperties bearerTokenProperties) {
        String jwtSchemeName = "Authorization";

        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList(jwtSchemeName);

        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.name(jwtSchemeName)
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme(GrantType.BEARER.getType());

        Components components = new Components();
        components.addSecuritySchemes(jwtSchemeName, securityScheme);

        if (bearerTokenProperties.isEnabled() && !bearerTokenProperties.getTokens().isEmpty()) {
            List<BearerTokenProperties.BearerToken> tokens = bearerTokenProperties.getTokens();

            String description = tokens.stream()
                    .map(item -> String.format("**%s** %s", item.getName(), item.getToken()))
                    .collect(Collectors.joining("\n\n"));

            securityScheme.description(description);
        }

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components);
    }

}