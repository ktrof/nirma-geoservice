package org.nirma.repository;

import org.nirma.model.Accident;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface AccidentRepository extends ReactiveCrudRepository<Accident, String> {

    @Query("{'properties.?0' : ?1}")
    Flux<Accident> getFeaturesByProperty(String property, String value);

}
