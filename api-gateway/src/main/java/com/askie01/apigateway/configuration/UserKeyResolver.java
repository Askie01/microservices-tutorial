package com.askie01.apigateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class UserKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        final String user = exchange
                .getRequest()
                .getHeaders()
                .getFirst("user");
        return Mono
                .justOrEmpty(user)
                .defaultIfEmpty("anonymous");
    }
}
