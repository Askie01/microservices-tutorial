package com.askie01.apigateway.configuration;

import com.askie01.apigateway.converter.JwtAuthorityConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;

@Configuration
public class ReactiveJwtAuthenticationConverterAdapterConfiguration {

    @Bean
    public ReactiveJwtAuthenticationConverterAdapter reactiveJwtAuthenticationConverterAdapter(JwtAuthorityConverter jwtAuthorityConverter) {
        final JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtAuthorityConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }
}
