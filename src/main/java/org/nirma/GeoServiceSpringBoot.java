package org.nirma;

import lombok.RequiredArgsConstructor;
import org.nirma.model.Accident;
import org.nirma.model.District;
import org.nirma.model.Feature;
import org.nirma.model.FeatureCollection;
import org.nirma.repository.DistrictRepository;
import org.nirma.service.GeoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;

@SpringBootApplication
@EnableConfigurationProperties
@EnableWebFlux
@RequiredArgsConstructor
public class GeoServiceSpringBoot {

    private final GeoService geoService;

    public static void main(String[] args) {
        SpringApplication.run(GeoServiceSpringBoot.class, args);
    }

    @PostConstruct
    public void createDistricts() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Jackson2JsonDecoder jackson2JsonDecoder = new Jackson2JsonDecoder();

        Resource spbDistrictResource = resourceLoader.getResource("classpath:spb_districts.json");
        Flux<DataBuffer> spbDistrictInput = DataBufferUtils.read(spbDistrictResource, new DefaultDataBufferFactory(), 4096);
        jackson2JsonDecoder.decode(spbDistrictInput, ResolvableType.forType(Feature.class), null, Collections.emptyMap())
                .cast(Feature.class)
                .map(District::new)
                .flatMap(geoService::saveDistrict)
                .subscribe();

        Resource accidentResource = resourceLoader.getResource("classpath:28k_address.json");
        Flux<DataBuffer> accidentInput = DataBufferUtils.read(accidentResource, new DefaultDataBufferFactory(), 4096);

        geoService.getAccidentsByTopic(null)
                .hasElements()
                .flatMapMany(hasElements -> {
                    if (!hasElements) {
                        return jackson2JsonDecoder.decode(accidentInput, ResolvableType.forType(Feature.class), null, Collections.emptyMap())
                                .cast(Feature.class)
                                .map(Accident::new)
                                .flatMap(geoService::saveAccident);
                    } else {
                        return Flux.empty();
                    }
                })
                .subscribe();

    }

    @PreDestroy
    public void removeDistricts() {
        geoService.deleteDistricts();
    }

}
