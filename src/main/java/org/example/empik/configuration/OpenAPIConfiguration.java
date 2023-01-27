package org.example.empik.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Empik Sample by MW")
                        .description("Empik Sample by MW")
                        .version("v.0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
