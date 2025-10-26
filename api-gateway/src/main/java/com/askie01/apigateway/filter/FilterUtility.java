package com.askie01.apigateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import java.util.NoSuchElementException;
import java.util.UUID;

public class FilterUtility {

    public static final String REQUEST_ID = "request-id";

    public String getRequestId(HttpHeaders headers) {
        return headers.get(REQUEST_ID)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Request id does not have a value"));
    }

    public boolean requestIdExists(HttpHeaders headers) {
        return headers.containsKey(REQUEST_ID);
    }

    public ServerWebExchange addRequestIdRequestHeader(ServerWebExchange exchange, String requestId) {
        return addRequestHeader(exchange, REQUEST_ID, requestId);
    }

    public ServerWebExchange addRequestHeader(ServerWebExchange exchange,
                                              String headerName,
                                              String... headerValues) {
        final ServerHttpRequest requestWithNewHeader = exchange
                .getRequest()
                .mutate()
                .header(headerName, headerValues)
                .build();
        return exchange
                .mutate()
                .request(requestWithNewHeader)
                .build();
    }

    public void addRequestIdResponseHeader(ServerWebExchange exchange, String requestIdValue) {
        addResponseHeader(exchange, REQUEST_ID, requestIdValue);
    }

    public void addResponseHeader(ServerWebExchange exchange,
                                  String headerName,
                                  String headerValue) {
        exchange.getResponse()
                .getHeaders()
                .add(headerName, headerValue);
    }

    public String generateRequestId() {
        return generateRandomUUID();
    }

    private String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }
}
