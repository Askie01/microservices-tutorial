package org.askie01.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MobilePhoneAlreadyExistsException extends RuntimeException {
    public MobilePhoneAlreadyExistsException(String message) {
        super(message);
    }
}
