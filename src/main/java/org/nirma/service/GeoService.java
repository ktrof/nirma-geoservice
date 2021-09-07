package org.nirma.service;

import org.nirma.model.Accident;
import org.nirma.model.District;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GeoService {

    Mono<Accident> saveAccident(Accident accident);

    Mono<District> saveDistrict(District district);

    Flux<Accident> getAccidentsByTopic(String topic);

    Flux<District> getDistrictByName(String name);

    void deleteDistricts();

    void deleteAccidents();

}
