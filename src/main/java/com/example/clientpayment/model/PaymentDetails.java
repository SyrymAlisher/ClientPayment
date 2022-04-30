package com.example.clientpayment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentDetails {
    private String paymentId;
    private ClientResponse clientId;
    private String  receiptName;
    private double cost;
}
