package org.nirma.repository;

import org.nirma.model.District;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DistrictRepository extends ReactiveCrudRepository<District, String> {

    @Query("{'properties.?0' : ?1}")
    Flux<District> getFeatureByProperty(String property, String value);

}
