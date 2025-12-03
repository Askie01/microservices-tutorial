package com.askie01.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class CustomersRouteLocatorConfiguration {

    @Bean
    public RouteLocator customersRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        final String currentDateTime = LocalDateTime.now().toString();
        return routeLocatorBuilder.routes()
                .route(configuration -> configuration
                        .path("/askie01/customers/**")
                        .filters(filter -> filter
                                .rewritePath("/askie01/customers(?:/(?<segment>.*))?", "/customers${segment}")
                                .addResponseHeader("X-Response-Time", currentDateTime))
                        .uri("lb://accounts"))  //TODO: Change it to customers service later on.
                .build();
    }
}