package org.nirma.service;

import lombok.RequiredArgsConstructor;
import org.nirma.model.Feature;
import org.nirma.repository.GeoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GeoServiceImpl implements GeoService {

    private static final String TOPIC = "topic";

    private final GeoRepository geoRepository;

    @Override
    public Mono<Feature> saveFeature(Feature feature) {
        feature.setRegisterDate(LocalDateTime.now());
        return geoRepository.save(feature);
    }

    @Override
    public Flux<Feature> getFeaturesByTopic(String topic) {
        return geoRepository.getFeaturesByProperty(TOPIC, topic);
    }
}
