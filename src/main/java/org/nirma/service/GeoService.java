package org.nirma.service;

import org.nirma.model.Feature;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GeoService {

    Mono<Feature> saveFeature(Feature feature);

    Flux<Feature> getFeaturesByTopic(String topic);

}
