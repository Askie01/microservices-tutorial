package com.askie01.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class LoansRouteLocatorConfiguration {

    @Bean
    public RouteLocator loansRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        final String currentDateTime = LocalDateTime.now().toString();
        return routeLocatorBuilder.routes()
                .route(configuration -> configuration
                        .path("/askie01/loans-service/**")
                        .filters(filter -> filter
                                .stripPrefix(2)
                                .addResponseHeader("X-Response-Time", currentDateTime))
                        .uri("lb://loans"))
                .build();
    }
}
