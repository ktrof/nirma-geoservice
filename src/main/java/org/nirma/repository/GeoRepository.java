package org.nirma.repository;

import org.nirma.model.Feature;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface GeoRepository extends ReactiveCrudRepository<Feature, String> {

    @Query("{'properties' : {?0 : ?1}}")
    Flux<Feature> getFeaturesByProperty(String property, String value);

}
