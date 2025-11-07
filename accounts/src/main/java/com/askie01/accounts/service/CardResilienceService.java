package com.askie01.accounts.service;

import com.askie01.accounts.dto.CardDTO;

import java.util.concurrent.CompletableFuture;

public interface CardResilienceService {
    CompletableFuture<CardDTO> getCard(String mobileNumber);
}
