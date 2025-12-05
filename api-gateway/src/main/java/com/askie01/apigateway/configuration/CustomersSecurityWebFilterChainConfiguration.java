package com.askie01.apigateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@Configuration
@EnableWebFluxSecurity
public class CustomersSecurityWebFilterChainConfiguration {

    private static final String CUSTOMERS_ENDPOINT = "/askie01/customers-service/**";

    @Bean
    public SecurityWebFilterChain customersSecurityWebFilterChain(ServerHttpSecurity http,
                                                         ReactiveJwtAuthenticationConverterAdapter authenticationConverter) {
        http.securityMatcher(ServerWebExchangeMatchers.pathMatchers(CUSTOMERS_ENDPOINT));
        http.authorizeExchange(configuration -> configuration
                .pathMatchers(HttpMethod.POST, CUSTOMERS_ENDPOINT).hasAuthority("customers:create")
                .pathMatchers(HttpMethod.GET, CUSTOMERS_ENDPOINT).hasAuthority("customers:read")
                .pathMatchers(HttpMethod.PUT, CUSTOMERS_ENDPOINT).hasAuthority("customers:update")
                .pathMatchers(HttpMethod.DELETE, CUSTOMERS_ENDPOINT).hasAuthority("customers:delete"));
        http.oauth2ResourceServer(configuration -> configuration
                .jwt(jwtConfiguration -> jwtConfiguration.jwtAuthenticationConverter(authenticationConverter)));
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
