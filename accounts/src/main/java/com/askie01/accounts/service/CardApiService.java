package com.askie01.accounts.service;

import com.askie01.accounts.dto.CardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardApiService implements CardService {

    private final CardResilienceService cardResilienceService;

    @Override
    public CardDTO getCard(String mobileNumber) {
        return cardResilienceService.getCard(mobileNumber).join();
    }
}
