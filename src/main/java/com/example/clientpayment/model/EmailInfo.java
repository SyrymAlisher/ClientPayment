package com.example.clientpayment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailInfo {
    private String id;
    private String clientName;
    private String clientSurname;
    private String clientEmail;
    private HashMap<String, Double> payments;
    private Double totalPayment;
}
