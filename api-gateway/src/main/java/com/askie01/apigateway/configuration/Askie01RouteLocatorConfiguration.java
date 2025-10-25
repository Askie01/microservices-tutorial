package com.askie01.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Askie01RouteLocatorConfiguration {

    @Bean
    public RouteLocator askie01RouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(path -> path
                        .path("/askie01/accounts/**")
                        .filters(filter -> filter.rewritePath("/askie01/accounts/(?<segment>.*)", "/${segment}"))
                        .uri("lb://accounts"))
                .route(path -> path
                        .path("askie01/cards/**")
                        .filters(filter -> filter.rewritePath("/askie01/cards/(?<segment>.*)", "/${segment}"))
                        .uri("lb://cards"))
                .route(path -> path
                        .path("/askie01/loans/**")
                        .filters(filter -> filter.rewritePath("/askie01/loans/(?<segment>.*)", "/${segment}"))
                        .uri("lb://loans"))
                .build();
    }
}
