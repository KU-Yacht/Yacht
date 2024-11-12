package site.yacht.backend.global.config.swagger;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("springdoc")
public class ServerProperties {

    private final List<ServerInformation> servers;

    public record ServerInformation(String url, String description) {
    }
}
