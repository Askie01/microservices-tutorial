package com.askie01.apigateway.configuration;

import com.askie01.apigateway.converter.JwtAuthorityConverter;
import com.askie01.apigateway.converter.KeyCloakJwtAuthorityConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyCloakJwtAuthorityConverterConfiguration {

    @Bean
    public JwtAuthorityConverter jwtAuthorityConverter() {
        return new KeyCloakJwtAuthorityConverter();
    }
}
