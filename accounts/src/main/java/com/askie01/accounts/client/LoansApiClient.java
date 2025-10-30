package com.askie01.accounts.client;

import com.askie01.accounts.dto.LoanDTO;
import com.askie01.accounts.fallback.LoansApiClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans", path = "/loans", fallback = LoansApiClientFallback.class)
public interface LoansApiClient {

    @GetMapping
    ResponseEntity<LoanDTO> getLoan(@RequestParam String mobileNumber);
}
