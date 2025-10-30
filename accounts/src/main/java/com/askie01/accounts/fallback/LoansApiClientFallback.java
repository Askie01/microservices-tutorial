package com.askie01.accounts.fallback;

import com.askie01.accounts.client.LoansApiClient;
import com.askie01.accounts.dto.LoanDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansApiClientFallback implements LoansApiClient {

    @Override
    public ResponseEntity<LoanDTO> getLoan(String mobileNumber) {
        return null;
    }
}
