package com.Polsoftex.ZdroweJedzeniePayment;

import com.Polsoftex.ZdroweJedzeniePayment.model.Card;
import com.Polsoftex.ZdroweJedzeniePayment.model.PaymentDTO;
import com.Polsoftex.ZdroweJedzeniePayment.paymentProcessors.PaymentProcessor;
import com.Polsoftex.ZdroweJedzeniePayment.services.PaymentDataInvalidException;
import com.Polsoftex.ZdroweJedzeniePayment.services.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

class PaymentServiceTests {

    private static PaymentService paymentService;

    @BeforeAll
    static void initializeService() {
        PaymentProcessor paymentProcessor = mock(PaymentProcessor.class);
        doNothing().when(paymentProcessor);
        paymentService = new PaymentService(paymentProcessor);
    }

    @Test
    void negativePaymentValueShouldFail() {
        PaymentDTO paymentDTO = new PaymentDTO(-10.23f,
                new Card("4556123430138743", "12/99", "123"));
        Assertions.assertThrows(PaymentDataInvalidException.class,
                () -> paymentService.performPayment(paymentDTO));
    }

    @Test
    void invalidCardNumberShouldFail() {
        PaymentDTO paymentDTO = new PaymentDTO(10.23f,
                new Card("1234123412341234", "12/99", "123"));
        Assertions.assertThrows(PaymentDataInvalidException.class,
                () -> paymentService.performPayment(paymentDTO));
    }

    @Test
    void expiredCardShouldFail() {
        PaymentDTO paymentDTO = new PaymentDTO(10.23f,
                new Card("4556123430138743", "12/10", "123"));
        Assertions.assertThrows(PaymentDataInvalidException.class,
                () -> paymentService.performPayment(paymentDTO));
    }

    @Test
    void validPaymentShouldPass() {
        PaymentDTO paymentDTO = new PaymentDTO(10.23f,
                new Card("4556123430138743", "12/99", "123"));
        paymentService.performPayment(paymentDTO);
    }
}
