package org.nirma.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.nirma.model.Feature;
import org.nirma.model.InlineResponse400;
import org.nirma.model.InlineResponse401;
import org.nirma.model.InlineResponse500;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FeatureRouter {

    @Bean
    @RouterOperation(operation = @Operation(
            operationId = "findFeaturesByTopic", summary = "Get an array of Feature objects",
            parameters = {
                    @Parameter(name = "topic", description = "Topic of the accident")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful response", content = @Content(mediaType = MediaType.APPLICATION_STREAM_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Feature.class)))),
                    @ApiResponse(responseCode = "400", description = "The JSON is not valid.", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
                    @ApiResponse(responseCode = "401", description = "The request requires an user authentication.", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occured.", content = @Content(schema = @Schema(implementation = InlineResponse500.class)))
            })
    )
    public RouterFunction<ServerResponse> findFeaturesByTopic(FeatureHandler featureHandler) {
        return RouterFunctions.route()
                .GET("/features", featureHandler::findFeaturesByTopic)
                .build();
    }

}
