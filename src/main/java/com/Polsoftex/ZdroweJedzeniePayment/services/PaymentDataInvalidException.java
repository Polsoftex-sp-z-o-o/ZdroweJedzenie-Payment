package com.Polsoftex.ZdroweJedzeniePayment.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Payment data invalid!")
public class PaymentDataInvalidException extends RuntimeException {
    public PaymentDataInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
