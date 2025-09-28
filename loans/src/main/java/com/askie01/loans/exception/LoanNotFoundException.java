package com.askie01.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String mobileNumber) {
        super(String.format("Loan with mobile number: '%s' doesn't exist.", mobileNumber));
    }

    public LoanNotFoundException(Long number) {
        super(String.format("Loan with number: '%d' doesn't exist.", number));
    }
}
