package com.askie01.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class CardsRouteLocatorConfiguration {

    @Bean
    public RouteLocator cardsRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        final String currentDateTime = LocalDateTime.now().toString();
        return routeLocatorBuilder.routes()
                .route(configuration -> configuration
                        .path("/askie01/cards/**")
                        .filters(filter -> filter
                                .rewritePath("/askie01/cards(?:/(?<segment>.*))?", "/cards${segment}")
                                .addResponseHeader("X-Response-Time", currentDateTime))
                        .uri("lb://cards"))
                .build();
    }
}
