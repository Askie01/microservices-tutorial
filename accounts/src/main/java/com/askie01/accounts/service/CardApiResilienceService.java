package com.askie01.accounts.service;

import com.askie01.accounts.client.CardsApiClient;
import com.askie01.accounts.dto.CardDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CardApiResilienceService implements CardResilienceService {

    private final CardsApiClient cardsApiClient;

    @Override
    @CircuitBreaker(name = "cards-api-client-circuit-breaker")
    @TimeLimiter(name = "cards-api-client-time-limiter")
    @Retry(name = "cards-api-client-retry")
    public CompletableFuture<CardDTO> getCard(String mobileNumber) {
        return CompletableFuture.supplyAsync(() ->
                cardsApiClient.getCard(mobileNumber).getBody());
    }
}
