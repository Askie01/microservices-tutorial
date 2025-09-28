package org.askie01.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String mobileNumber) {
        super(String.format("Customer with mobile number: '%s' doesn't exist.", mobileNumber));
    }
}
