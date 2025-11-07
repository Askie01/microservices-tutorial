package com.askie01.accounts.service;

import com.askie01.accounts.dto.LoanDTO;

public interface LoanService {
    LoanDTO getLoan(String mobileNumber);
}
