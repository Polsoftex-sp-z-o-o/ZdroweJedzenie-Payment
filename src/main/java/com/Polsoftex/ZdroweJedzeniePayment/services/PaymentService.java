package com.Polsoftex.ZdroweJedzeniePayment.services;

import com.Polsoftex.ZdroweJedzeniePayment.model.Card;
import com.Polsoftex.ZdroweJedzeniePayment.services.exceptions.CardInvalidException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public void performPayment(Card card) {
        if (validateCardData(card)) {
            sendPaymentRequest();
        } else {
            throw new CardInvalidException("Card failed validation!");
        }
    }

    private void sendPaymentRequest() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }

    private boolean validateCardData(Card card) {
        // TODO
        return true;
    }
}
