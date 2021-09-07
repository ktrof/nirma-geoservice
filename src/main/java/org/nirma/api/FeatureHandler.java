package org.nirma.api;

import lombok.RequiredArgsConstructor;
import org.nirma.model.Accident;
import org.nirma.model.District;
import org.nirma.service.GeoService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FeatureHandler {

    private final GeoService geoService;

    public Mono<ServerResponse> findAccidentsByTopic(ServerRequest serverRequest) {
        Flux<Accident> accidents = geoService.getAccidentsByTopic(serverRequest.queryParam("topic").orElse(null));
        return accidents
                .hasElements()
                .flatMap(hasElements -> {
                    if (hasElements) {
                        return ServerResponse.ok()
                                .contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(accidents, Accident.class);
                    } else {
                        return ServerResponse.notFound().build();
                    }
                });
    }

    public Mono<ServerResponse> findDistrictByName(ServerRequest serverRequest) {
        Flux<District> district = geoService.getDistrictByName(serverRequest.queryParam("name").orElse(null));
        return district
                .hasElements()
                .flatMap(hasElements -> {
                    if (hasElements) {
                        return ServerResponse.ok()
                                .contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(district, District.class);
                    } else {
                        return ServerResponse.notFound().build();
                    }
                });
    }

}
