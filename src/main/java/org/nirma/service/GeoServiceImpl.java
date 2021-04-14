package org.nirma.service;

import lombok.RequiredArgsConstructor;
import org.nirma.model.FeatureCollection;
import org.nirma.repository.GeoRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableAsync
public class GeoServiceImpl implements GeoService {

    private final GeoRepository geoRepository;

    @Async
    @Override
    public void saveFeatureCollection(FeatureCollection featureCollection) {
        geoRepository.deleteAll();
        featureCollection.getFeatures().forEach(geoRepository::save);
    }
}
