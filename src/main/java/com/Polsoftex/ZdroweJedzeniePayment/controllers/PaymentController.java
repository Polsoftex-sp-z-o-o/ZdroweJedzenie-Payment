package com.Polsoftex.ZdroweJedzeniePayment.controllers;

import com.Polsoftex.ZdroweJedzeniePayment.model.Card;
import com.Polsoftex.ZdroweJedzeniePayment.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("payment/{orderId}")
    public void performPayment(@RequestBody Card card) {
        paymentService.performPayment(card);
    }
}
