package com.askie01.accounts.fallback;

import com.askie01.accounts.client.CardsApiClient;
import com.askie01.accounts.dto.CardDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsApiClientFallback implements CardsApiClient {

    @Override
    public ResponseEntity<CardDTO> getCard(String mobileNumber) {
        return null;
    }
}
