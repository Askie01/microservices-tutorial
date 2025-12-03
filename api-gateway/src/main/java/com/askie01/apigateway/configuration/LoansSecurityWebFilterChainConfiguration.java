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
public class LoansSecurityWebFilterChainConfiguration {

    private static final String LOANS_ENDPOINT = "/askie01/loans/**";

    @Bean
    public SecurityWebFilterChain loansSecurityWebFilterChain(ServerHttpSecurity http,
                                                              ReactiveJwtAuthenticationConverterAdapter authenticationConverter) {
        http.securityMatcher(ServerWebExchangeMatchers.pathMatchers(LOANS_ENDPOINT));
        http.authorizeExchange(configuration -> configuration
                .pathMatchers(HttpMethod.POST, LOANS_ENDPOINT).hasAuthority("loans:create")
                .pathMatchers(HttpMethod.GET, LOANS_ENDPOINT).hasAuthority("loans:read")
                .pathMatchers(HttpMethod.PUT, LOANS_ENDPOINT).hasAuthority("loans:update")
                .pathMatchers(HttpMethod.DELETE, LOANS_ENDPOINT).hasAuthority("loans:delete"));
        http.oauth2ResourceServer(configuration -> configuration
                .jwt(jwtConfiguration -> jwtConfiguration.jwtAuthenticationConverter(authenticationConverter)));
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
