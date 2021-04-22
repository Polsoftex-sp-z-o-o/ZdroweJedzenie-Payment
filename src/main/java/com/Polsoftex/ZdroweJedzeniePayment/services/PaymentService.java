package com.Polsoftex.ZdroweJedzeniePayment.services;

import com.Polsoftex.ZdroweJedzeniePayment.model.Card;
import com.Polsoftex.ZdroweJedzeniePayment.services.exceptions.CardInvalidException;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }

    private boolean validateCardData(Card card) {
        boolean numberValid = validateCardNumber(card.getNumber());
        boolean dateValid = validateCardExpirationDate(card.getExpiration());
        return numberValid && dateValid;
    }

    private boolean validateCardNumber(String number) {
        CreditCardValidator validator = new CreditCardValidator();
        return validator.isValid(number);
    }

    private boolean validateCardExpirationDate(String expiration) {
        DateTimeFormatter ccMonthFormatter = DateTimeFormatter.ofPattern("MM/uu");
        YearMonth lastValidMonth;

        // Check if the expiration date is in a valid format
        try {
            lastValidMonth = YearMonth.parse(expiration, ccMonthFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }

        // Check if the card is not expired
        YearMonth now = YearMonth.now(ZoneOffset.UTC);
        return !now.isAfter(lastValidMonth);
    }
}
