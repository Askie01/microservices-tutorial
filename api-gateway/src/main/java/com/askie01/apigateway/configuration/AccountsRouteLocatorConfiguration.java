package com.askie01.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class AccountsRouteLocatorConfiguration {

    @Bean
    public RouteLocator accountsRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        final String currentDateTime = LocalDateTime.now().toString();
        return routeLocatorBuilder.routes()
                .route(config -> config
                        .path("/askie01/accounts-service/**")
                        .filters(filter -> filter
                                .stripPrefix(2)
                                .addResponseHeader("X-Response-Time", currentDateTime))
                        .uri("lb://accounts"))
                .build();
    }
}
