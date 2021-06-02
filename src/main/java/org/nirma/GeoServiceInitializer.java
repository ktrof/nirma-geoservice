package org.nirma;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.nirma.model.Feature;
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

        Flux.<Feature>create(fluxSink -> {
            messageListenerContainer.setMessageListener(message -> {
                try {
                    if (message.getBody() != null) {
                        Feature feature = objectMapper.readValue(message.getBody(), Feature.class);
                        fluxSink.next(feature);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fluxSink.onRequest(value -> messageListenerContainer.start());
            fluxSink.onDispose(messageListenerContainer::stop);
        })
                .flatMap(geoService::saveFeature)
                .subscribe();

    }

}
