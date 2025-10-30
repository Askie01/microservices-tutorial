package com.askie01.accounts.client;

import com.askie01.accounts.dto.CardDTO;
import com.askie01.accounts.fallback.CardsApiClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards", path = "/cards", fallback = CardsApiClientFallback.class)
public interface CardsApiClient {

    @GetMapping
    ResponseEntity<CardDTO> getCard(@RequestParam String mobileNumber);
}
