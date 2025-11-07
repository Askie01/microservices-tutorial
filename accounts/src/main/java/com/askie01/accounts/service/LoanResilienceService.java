package com.askie01.accounts.service;

import com.askie01.accounts.dto.LoanDTO;

import java.util.concurrent.CompletableFuture;

public interface LoanResilienceService {
    CompletableFuture<LoanDTO> getLoan(String mobileNumber);
}
