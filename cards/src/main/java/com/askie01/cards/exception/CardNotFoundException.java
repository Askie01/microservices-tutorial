package com.askie01.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String mobileNumber) {
        super(String.format("Card with mobile number: '%s' doesn't exist.", mobileNumber));
    }

    public CardNotFoundException(Long number) {
        super(String.format("Card with number: '%d' doesn't exist.", number));
    }
}
