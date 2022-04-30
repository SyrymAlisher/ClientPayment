package com.example.clientpayment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentTotal {
    private ClientResponse clientResponse;
    private int numberOfPayments;
    private double totalPayment;
}
