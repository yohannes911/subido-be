package org.subido.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${info.app.name}") String name,
                           @Value("${info.app.version}") String appVersion,
                           @Value("${info.app.description}") String description) {
        return new OpenAPI()
                .info(new Info()
                        .title(name)
                        .description(description)
                        .version(appVersion));
    }
}
