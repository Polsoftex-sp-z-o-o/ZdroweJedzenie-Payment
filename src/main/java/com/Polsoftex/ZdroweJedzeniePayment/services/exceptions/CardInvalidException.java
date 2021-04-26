package com.Polsoftex.ZdroweJedzeniePayment.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Card data invalid!")
public class CardInvalidException extends RuntimeException {
    public CardInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
