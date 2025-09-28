package com.askie01.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistsException extends RuntimeException {
    public CardAlreadyExistsException(String mobileNumber) {
        super(String.format("Card with mobile number: '%s' already exists.", mobileNumber));
    }
}
