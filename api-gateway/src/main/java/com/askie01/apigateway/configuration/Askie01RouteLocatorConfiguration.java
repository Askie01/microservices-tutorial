package com.askie01.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class Askie01RouteLocatorConfiguration {

    @Bean
    public RouteLocator askie01RouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        final String segmentReplacement = "/${segment}";
        final String responseTimeHeaderName = "X-Response-Time";
        final String responseTimeHeaderValue = LocalDateTime.now().toString();
        return routeLocatorBuilder.routes()
                .route(path -> path
                        .path("/askie01/accounts/**")
                        .filters(filter -> filter
                                .rewritePath("/askie01/accounts/(?<segment>.*)", segmentReplacement)
                                .addResponseHeader(responseTimeHeaderName, responseTimeHeaderValue))
                        .uri("lb://accounts"))
                .route(path -> path
                        .path("askie01/cards/**")
                        .filters(filter -> filter
                                .rewritePath("/askie01/cards/(?<segment>.*)", segmentReplacement)
                                .addResponseHeader(responseTimeHeaderName, responseTimeHeaderValue))
                        .uri("lb://cards"))
                .route(path -> path
                        .path("/askie01/loans/**")
                        .filters(filter -> filter
                                .rewritePath("/askie01/loans/(?<segment>.*)", segmentReplacement)
                                .addResponseHeader(responseTimeHeaderName, responseTimeHeaderValue))
                        .uri("lb://loans"))
                .build();
    }
}
