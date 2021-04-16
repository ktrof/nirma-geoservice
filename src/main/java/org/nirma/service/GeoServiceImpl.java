package org.nirma.service;

import lombok.RequiredArgsConstructor;
import org.nirma.model.Feature;
import org.nirma.model.FeatureCollection;
import org.nirma.repository.GeoRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@EnableAsync
public class GeoServiceImpl implements GeoService {

    private static final String TOPIC = "topic";

    private final GeoRepository geoRepository;

    @Async
    @Override
    public void saveFeatureCollection(FeatureCollection featureCollection) {
        geoRepository.deleteAll();
        featureCollection.getFeatures().forEach(geoRepository::save);
    }

    @Override
    public FeatureCollection getFeatureCollectionByTopic(String topic) {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put(TOPIC, topic);
        List<Feature> featuresByProperties = geoRepository.getFeaturesByProperties(propertyMap);
        return new FeatureCollection(featuresByProperties);
    }
}
