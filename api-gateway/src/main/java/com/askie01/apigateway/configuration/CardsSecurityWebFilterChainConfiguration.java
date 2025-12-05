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
public class CardsSecurityWebFilterChainConfiguration {

    private static final String CARDS_ENDPOINT = "/askie01/cards-service/**";

    @Bean
    public SecurityWebFilterChain cardsSecurityWehFilterChain(ServerHttpSecurity http,
                                                              ReactiveJwtAuthenticationConverterAdapter authenticationConverter) {
        http.securityMatcher(ServerWebExchangeMatchers.pathMatchers(CARDS_ENDPOINT));
        http.authorizeExchange(configuration -> configuration
                .pathMatchers(HttpMethod.POST, CARDS_ENDPOINT).hasAuthority("cards:create")
                .pathMatchers(HttpMethod.GET, CARDS_ENDPOINT).hasAuthority("cards:read")
                .pathMatchers(HttpMethod.PUT, CARDS_ENDPOINT).hasAuthority("cards:update")
                .pathMatchers(HttpMethod.DELETE, CARDS_ENDPOINT).hasAuthority("cards:delete"));
        http.oauth2ResourceServer(configuration -> configuration
                .jwt(jwtConfiguration -> jwtConfiguration.jwtAuthenticationConverter(authenticationConverter)));
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
