package org.nirma.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.nirma.model.Accident;
import org.nirma.model.District;
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

import java.net.URI;

@Configuration
public class FeatureRouter {

    @Bean
    @RouterOperation(operation = @Operation(
            operationId = "findAccidentsByTopic", summary = "Get an array of Accident objects",
            parameters = {
                    @Parameter(name = "topic", description = "Topic of the accident")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful response", content = @Content(mediaType = MediaType.TEXT_EVENT_STREAM_VALUE, array = @ArraySchema(schema = @Schema(implementation = Accident.class)))),
                    @ApiResponse(responseCode = "400", description = "The JSON is not valid.", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
                    @ApiResponse(responseCode = "401", description = "The request requires an user authentication.", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occured.", content = @Content(schema = @Schema(implementation = InlineResponse500.class)))
            })
    )
    public RouterFunction<ServerResponse> findAccidentsByTopic(FeatureHandler featureHandler) {
        return RouterFunctions.route()
                .GET("/accidents", featureHandler::findAccidentsByTopic)
                .build();
    }

    @Bean
    @RouterOperation(operation = @Operation(
            operationId = "findDistrictByName", summary = "Get a District object",
            parameters = {
                    @Parameter(name = "name", description = "Name of the district")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful response", content = @Content(mediaType = MediaType.TEXT_EVENT_STREAM_VALUE, array = @ArraySchema(schema = @Schema(implementation = District.class)))),
                    @ApiResponse(responseCode = "400", description = "The JSON is not valid.", content = @Content(schema = @Schema(implementation = InlineResponse400.class))),
                    @ApiResponse(responseCode = "401", description = "The request requires an user authentication.", content = @Content(schema = @Schema(implementation = InlineResponse401.class))),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occured.", content = @Content(schema = @Schema(implementation = InlineResponse500.class)))
            })
    )
    public RouterFunction<ServerResponse> findDistricts(FeatureHandler featureHandler) {
        return RouterFunctions.route()
                .GET("/districts", featureHandler::findDistrictByName)
                .build();
    }
}