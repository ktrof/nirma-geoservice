package org.nirma.api;

import lombok.RequiredArgsConstructor;
import org.nirma.model.Feature;
import org.nirma.service.GeoService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FeatureHandler {

    private final GeoService geoService;

    public Mono<ServerResponse> findFeaturesByTopic(ServerRequest serverRequest) {
        Mono<ServerResponse> responseMono;
        if (serverRequest.queryParam("topic").isPresent()) {
            responseMono = ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_STREAM_JSON)
                    .body(geoService.getFeaturesByTopic(serverRequest.queryParam("topic").get()), Feature.class);
        } else {
            responseMono = ServerResponse.badRequest().build();
        }
        return responseMono;
    }

}
