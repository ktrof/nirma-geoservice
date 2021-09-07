package org.nirma.service;

import lombok.RequiredArgsConstructor;
import org.nirma.model.Accident;
import org.nirma.model.District;
import org.nirma.repository.AccidentRepository;
import org.nirma.repository.DistrictRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GeoServiceImpl implements GeoService {

    private static final String TOPIC = "topic";
    private static final String DISTRICT_NAME = "name";

    private final AccidentRepository accidentRepository;
    private final DistrictRepository districtRepository;

    @Override
    public Mono<Accident> saveAccident(Accident accident) {
        return accidentRepository.save(accident);
    }

    @Override
    public Mono<District> saveDistrict(District district) {
        return districtRepository.save(district);
    }

    @Override
    public Flux<Accident> getAccidentsByTopic(String topic) {
        return topic != null ? accidentRepository.getFeaturesByProperty(TOPIC, topic) : accidentRepository.findAll();
    }

    @Override
    public Flux<District> getDistrictByName(String name) {
        return name != null ? districtRepository.getFeatureByProperty(DISTRICT_NAME, name) : districtRepository.findAll();
    }

    @Override
    public void deleteDistricts() {
        districtRepository.deleteAll().subscribe();
    }

    @Override
    public void deleteAccidents() {
        accidentRepository.deleteAll();
    }
}
