package com.askie01.apigateway.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class ResponseTraceFilter implements GlobalFilter {

    private final FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final HttpHeaders headers = exchange.getRequest().getHeaders();
        final boolean requestIdExists = filterUtility.requestIdExists(headers);

        if (requestIdExists) {
            final String requestId = filterUtility.getRequestId(headers);
            log.atDebug().log("Received request id: '{}'", requestId);
            addRequestIdResponseHeader(exchange, requestId);
        }
        return chain.filter(exchange);
    }

    private void addRequestIdResponseHeader(ServerWebExchange exchange, String correlationIdValue) {
        filterUtility.addRequestIdResponseHeader(exchange, correlationIdValue);
    }
}
