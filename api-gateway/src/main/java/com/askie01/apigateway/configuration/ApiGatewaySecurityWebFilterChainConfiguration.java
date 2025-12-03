package com.askie01.apigateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@Configuration
@EnableWebFluxSecurity
public class ApiGatewaySecurityWebFilterChainConfiguration {

    private static final String ACTUATOR_HEALTH_ENDPOINT = "/actuator/health/**";

    @Bean
    public SecurityWebFilterChain apiGatewaySecurityWebFilterChain(ServerHttpSecurity http) {
        http.securityMatcher(ServerWebExchangeMatchers.pathMatchers(ACTUATOR_HEALTH_ENDPOINT));
        http.authorizeExchange(configuration -> configuration
                .pathMatchers(ACTUATOR_HEALTH_ENDPOINT).permitAll());
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
