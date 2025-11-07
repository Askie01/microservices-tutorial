package com.askie01.accounts.service;

import com.askie01.accounts.client.LoansApiClient;
import com.askie01.accounts.dto.LoanDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class LoanApiResilienceService implements LoanResilienceService {

    private final LoansApiClient loansApiClient;

    @Override
    @CircuitBreaker(name = "loans-api-client-circuit-breaker")
    @TimeLimiter(name = "loans-api-client-time-limiter")
    @Retry(name = "loans-api-client-retry")
    public CompletableFuture<LoanDTO> getLoan(String mobileNumber) {
        return CompletableFuture.supplyAsync(() ->
                loansApiClient.getLoan(mobileNumber).getBody());
    }
}
