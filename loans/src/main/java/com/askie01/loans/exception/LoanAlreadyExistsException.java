package com.askie01.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsException extends RuntimeException {
    public LoanAlreadyExistsException(String mobileNumber) {
        super(String.format("Loan with mobile number: '%s' already exists.", mobileNumber));
    }
}
