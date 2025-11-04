package com.askie01.apigateway.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class Askie01RouteLocatorConfiguration {

    private final RedisRateLimiter redisRateLimiter;
    private final KeyResolver userKeyResolver;

    @Bean
    public RouteLocator askie01RouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        final String segmentReplacement = "/${segment}";
        final String responseTimeHeaderName = "X-Response-Time";
        final String responseTimeHeaderValue = LocalDateTime.now().toString();
        final Duration firstBackoffDelay = Duration.ofMillis(1000);
        final Duration maxBackoffDelay = Duration.ofSeconds(1);
        return routeLocatorBuilder.routes()
                .route(path -> path
                        .path("/askie01/accounts/**")
                        .filters(filter -> filter
                                .rewritePath("/askie01/accounts/(?<segment>.*)", segmentReplacement)
                                .addResponseHeader(responseTimeHeaderName, responseTimeHeaderValue)
                                .circuitBreaker(config -> config
                                        .setName("accounts-circuit-breaker")
                                        .setFallbackUri("forward:/fallback/contact-support")))
                        .uri("lb://accounts"))
                .route(path -> path
                        .path("askie01/cards/**")
                        .filters(filter -> filter
                                .rewritePath("/askie01/cards/(?<segment>.*)", segmentReplacement)
                                .addResponseHeader(responseTimeHeaderName, responseTimeHeaderValue)
                                .requestRateLimiter(config -> config
                                        .setRateLimiter(redisRateLimiter)
                                        .setKeyResolver(userKeyResolver)))
                        .uri("lb://cards"))
                .route(path -> path
                        .path("/askie01/loans/**")
                        .filters(filter -> filter
                                .rewritePath("/askie01/loans/(?<segment>.*)", segmentReplacement)
                                .addResponseHeader(responseTimeHeaderName, responseTimeHeaderValue)
                                .retry(config -> config
                                        .setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        .setBackoff(firstBackoffDelay, maxBackoffDelay, 2, true)))
                        .uri("lb://loans"))
                .build();
    }
}
