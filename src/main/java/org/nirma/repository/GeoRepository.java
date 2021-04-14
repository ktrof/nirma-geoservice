package org.nirma.repository;

import org.nirma.model.Feature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeoRepository extends MongoRepository<Feature, String> {
}
