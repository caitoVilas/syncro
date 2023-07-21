package com.caito.microservice.syncroservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author claudio.vilas
 * @version 07/2023
 */

@Configuration
public class SwaggerConfig {
    @Value("${application.version}")
    private String appVersion;
    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .group("syncro")
                .packagesToScan("com.caito.microservice.syncroservice")
                .build();
    }

    @Bean
    public OpenAPI springSyncroOpenApi(){
        return new OpenAPI()
                .info(new Info().title("Servicio de Sincronizacion")
                        .version(appVersion)
                        .contact(new Contact().email("caitocd@gmail.com")));
    }
}
