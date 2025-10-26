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
public class RequestTraceFilter implements GlobalFilter {

    private final FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final HttpHeaders headers = exchange.getRequest().getHeaders();
        final boolean requestIdExists = requestIdExists(headers);

        if (requestIdExists) {
            final String requestId = getRequestId(headers);
            log.atDebug().log("Received request id: '{}'", requestId);
            return chain.filter(exchange);
        }

        final String requestId = generateRequestId();
        final ServerWebExchange mutatedExchange = createServerWebExchangeWithRequestId(exchange, requestId);
        log.atDebug().log("Generated request id: '{}'", requestId);
        return chain.filter(mutatedExchange);
    }

    private ServerWebExchange createServerWebExchangeWithRequestId(ServerWebExchange exchange, String requestId) {
        return filterUtility.addRequestIdRequestHeader(exchange, requestId);
    }

    private String getRequestId(HttpHeaders headers) {
        return filterUtility.getRequestId(headers);
    }

    private boolean requestIdExists(HttpHeaders headers) {
        return filterUtility.requestIdExists(headers);
    }

    private String generateRequestId() {
        return filterUtility.generateRequestId();
    }
}
