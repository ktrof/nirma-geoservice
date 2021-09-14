package org.nirma.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "nirma")
public class SpringDocConfiguration {

    private String apiurl;

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.url(apiurl).description("Geoservice Server URL");
        return new OpenAPI().servers(Collections.singletonList(server));
    }

}