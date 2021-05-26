package org.nirma.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nirma.model.FeatureCollection;
import org.nirma.service.GeoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-09T07:56:43.078Z[GMT]")
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class GeometryApiController implements GeometryApi {

    private final HttpServletRequest request;
    private final GeoService geoService;

    public ResponseEntity<FeatureCollection> featureCollectionGetByTopic(@RequestParam String topic) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<FeatureCollection>(geoService.getFeatureCollectionByTopic(topic), HttpStatus.OK);
        }

        return new ResponseEntity<FeatureCollection>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> featureCollectionPost(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema())
                                                      @Valid @RequestBody FeatureCollection body) {
        String accept = request.getHeader("Accept");
        geoService.saveFeatureCollection(body);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
