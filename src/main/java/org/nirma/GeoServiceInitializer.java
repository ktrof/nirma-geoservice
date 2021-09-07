package org.nirma;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.nirma.model.Accident;
import org.nirma.model.FeatureCollection;
import org.nirma.service.GeoService;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class GeoServiceInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final SimpleMessageListenerContainer messageListenerContainer;
    private final GeoService geoService;
    private final ObjectMapper objectMapper;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        Flux.<FeatureCollection>create(fluxSink -> {
            messageListenerContainer.setMessageListener(message -> {
                geoService.deleteAccidents();
                try {
                    if (message.getBody() != null) {
                        FeatureCollection featureCollection = objectMapper.readValue(message.getBody(), FeatureCollection.class);
                        fluxSink.next(featureCollection);
                    }
                } catch (IOException e) {
                    fluxSink.error(e);
                }
            });
            fluxSink.onRequest(value -> messageListenerContainer.start());
            fluxSink.onDispose(messageListenerContainer::stop);
        })
                .flatMapIterable(FeatureCollection::getFeatures)
                .map(Accident::new)
                .flatMap(geoService::saveAccident)
                .subscribe();

    }

}
