package com.example.clientpayment.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentResponse {
    private String paymentId;
    private String clientId;
    private String  receiptName;
    private double cost;
}
