package com.askie01.loans.service;

import com.askie01.loans.dto.LoanDTO;
import com.askie01.loans.entity.Loan;

public interface LoanService {
    Loan createLoan(String mobileNumber);

    Loan getLoan(String mobileNumber);

    Loan updateLoan(LoanDTO loanDTO);

    Loan deleteLoan(String mobileNumber);
}
