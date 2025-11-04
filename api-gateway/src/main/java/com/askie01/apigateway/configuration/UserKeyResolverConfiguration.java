package com.askie01.apigateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserKeyResolverConfiguration {

    @Bean
    public KeyResolver userKeyResolver() {
        return new UserKeyResolver();
    }
}
