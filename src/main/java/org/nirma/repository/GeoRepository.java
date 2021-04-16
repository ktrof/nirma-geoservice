package org.nirma.repository;

import org.nirma.model.Feature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GeoRepository extends MongoRepository<Feature, String> {

    List<Feature> getFeaturesByProperties(Map<String, Object> property);

}
