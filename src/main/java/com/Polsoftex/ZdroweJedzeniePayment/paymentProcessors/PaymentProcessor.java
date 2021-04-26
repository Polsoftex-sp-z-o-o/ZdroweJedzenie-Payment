package com.Polsoftex.ZdroweJedzeniePayment.paymentProcessors;

import com.Polsoftex.ZdroweJedzeniePayment.model.PaymentDTO;

public interface PaymentProcessor {
    void processPayment(PaymentDTO paymentDTO);
}
