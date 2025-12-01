package com.askie01.apigateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
                                                            ReactiveJwtAuthenticationConverterAdapter authenticationConverter) {
        http.authorizeExchange(config -> config
                .pathMatchers("askie01/accounts/**").hasAuthority("ACCOUNTS")
                .pathMatchers("askie01/loans/**").hasAuthority("LOANS")
                .pathMatchers("askie01/cards/**").hasAuthority("CARDS"));
        http.oauth2ResourceServer(config -> config
                .jwt(jwtConfiguration -> jwtConfiguration
                        .jwtAuthenticationConverter(authenticationConverter)));
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
