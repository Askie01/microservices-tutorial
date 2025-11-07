package com.askie01.accounts.service;

import com.askie01.accounts.dto.LoanDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanApiService implements LoanService {

    private final LoanResilienceService loanResilienceService;

    @Override
    public LoanDTO getLoan(String mobileNumber) {
        return loanResilienceService.getLoan(mobileNumber).join();
    }
}
