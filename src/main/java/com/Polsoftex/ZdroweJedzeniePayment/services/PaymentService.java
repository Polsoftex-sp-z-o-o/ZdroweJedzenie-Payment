package com.Polsoftex.ZdroweJedzeniePayment.services;

import com.Polsoftex.ZdroweJedzeniePayment.model.Card;
import com.Polsoftex.ZdroweJedzeniePayment.model.PaymentDTO;
import com.Polsoftex.ZdroweJedzeniePayment.paymentProcessors.PaymentProcessor;
import org.apache.commons.validator.routines.CreditCardValidator;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class PaymentService {

    private PaymentProcessor paymentProcessor;

    public PaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void performPayment(PaymentDTO paymentDTO) {
        if (validatePaymentData(paymentDTO)) {
            paymentProcessor.processPayment(paymentDTO);
        } else {
            throw new PaymentDataInvalidException("Payment data invalid!");
        }
    }

    private boolean validatePaymentData(PaymentDTO paymentDTO) {
        return validatePaymentValue(paymentDTO.getValue()) && validateCardData(paymentDTO.getCard());
    }

    private boolean validatePaymentValue(float value) {
        return value > 0;
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
