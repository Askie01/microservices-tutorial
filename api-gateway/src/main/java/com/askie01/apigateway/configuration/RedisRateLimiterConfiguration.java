package com.askie01.apigateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisRateLimiterConfiguration {

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        final int defaultReplenishRate = 1;
        final int defaultBurstCapacity = 1;
        final int defaultRequestedTokens = 1;
        return new RedisRateLimiter(defaultReplenishRate, defaultBurstCapacity, defaultRequestedTokens);
    }
}
