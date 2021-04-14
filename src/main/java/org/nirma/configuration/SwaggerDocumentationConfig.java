package org.nirma.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-09T07:56:43.078Z[GMT]")
@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("org.nirma.api"))
                    .build()
                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("GeoJSON format")
            .description("This document defines the GeoJSON format as an OpenAPI. It contains the definitions for 'Feature' object and 'FeatureCollection' objects, as well as the definitions for all 'Geometry' objects. It conforms with the 'RFC-7946' standard from IETF (August 2016 version) Kudos to @bubbobne and @idkw whose code helped me not start from scratch https://gist.github.com/bubbobne/fe5f2db65acf039be6a9fd92fc9c7233 ")
            .license("GPLv3")
            .licenseUrl("https://www.gnu.org/licenses/gpl-3.0.html")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("","", "zitoun@gmail.com"))
            .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("GeoJSON format")
                .description("This document defines the GeoJSON format as an OpenAPI. It contains the definitions for 'Feature' object and 'FeatureCollection' objects, as well as the definitions for all 'Geometry' objects. It conforms with the 'RFC-7946' standard from IETF (August 2016 version) Kudos to @bubbobne and @idkw whose code helped me not start from scratch https://gist.github.com/bubbobne/fe5f2db65acf039be6a9fd92fc9c7233 ")
                .termsOfService("")
                .version("1.0.0")
                .license(new License()
                    .name("GPLv3")
                    .url("https://www.gnu.org/licenses/gpl-3.0.html"))
                .contact(new io.swagger.v3.oas.models.info.Contact()
                    .email("zitoun@gmail.com")));
    }

}
