package com.Polsoftex.ZdroweJedzeniePayment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private String number;
    private String expiration;
    private String code;
}
