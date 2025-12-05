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
public class AccountsSecurityWebFilterChainConfiguration {

    private static final String ACCOUNTS_ENDPOINT = "/askie01/accounts-service/**";

    @Bean
    public SecurityWebFilterChain accountsSecurityWebFilterChain(ServerHttpSecurity http,
                                                                 ReactiveJwtAuthenticationConverterAdapter authenticationConverter) {
        http.securityMatcher(ServerWebExchangeMatchers.pathMatchers(ACCOUNTS_ENDPOINT));
        http.authorizeExchange(configuration -> configuration
                .pathMatchers(HttpMethod.POST, ACCOUNTS_ENDPOINT).hasAuthority("accounts:create")
                .pathMatchers(HttpMethod.GET, ACCOUNTS_ENDPOINT).hasAuthority("accounts:read")
                .pathMatchers(HttpMethod.PUT, ACCOUNTS_ENDPOINT).hasAuthority("accounts:update")
                .pathMatchers(HttpMethod.DELETE, ACCOUNTS_ENDPOINT).hasAuthority("accounts:delete"));
        http.oauth2ResourceServer(configuration -> configuration
                .jwt(jwtConfiguration -> jwtConfiguration.jwtAuthenticationConverter(authenticationConverter)));
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
