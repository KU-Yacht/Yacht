package site.yacht.backend.global.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
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
    public OpenAPI openAPI(BearerTokenProperties bearerTokenProperties, ServerProperties serverProperties) {
        String jwtSchemeName = "Authorization";

        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList(jwtSchemeName);

        SecurityScheme securityScheme = new SecurityScheme();
        securityScheme.name(jwtSchemeName)
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme(GrantType.BEARER.getType())
                .description(getBearerTokenDescriptionFrom(bearerTokenProperties));

        Components components = new Components();
        components.addSecuritySchemes(jwtSchemeName, securityScheme);

        List<Server> servers = getServersFrom(serverProperties);

        return new OpenAPI()
                .servers(servers)
                .components(components)
                .addSecurityItem(securityRequirement);
    }

    private String getBearerTokenDescriptionFrom(BearerTokenProperties bearerTokenProperties) {
        if (!bearerTokenProperties.isEnabled() || bearerTokenProperties.getTokens() == null) {
            return null;
        }

        List<BearerTokenProperties.BearerToken> tokens = bearerTokenProperties.getTokens();
        return tokens.stream()
                .map(item -> String.format("**%s** %s", item.name(), item.token()))
                .collect(Collectors.joining("\n\n"));
    }

    private List<Server> getServersFrom(ServerProperties serverProperties) {
        if (serverProperties.getServers() == null) {
            return List.of();
        }

        return serverProperties.getServers().stream()
                .map(information -> {
                    Server server = new Server();
                    server.setUrl(information.url());
                    server.setDescription(information.description());
                    return server;
                })
                .toList();

    }

}