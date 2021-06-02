package org.nirma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableConfigurationProperties
@EnableWebFlux
public class GeoServiceSpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(GeoServiceSpringBoot.class, args);
    }

}
