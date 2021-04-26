package com.Polsoftex.ZdroweJedzeniePayment.paymentProcessors;

import com.Polsoftex.ZdroweJedzeniePayment.model.PaymentDTO;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class DummyPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(PaymentDTO paymentDTO) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }
}
