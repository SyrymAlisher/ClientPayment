package com.example.clientpayment.service;

import com.example.clientpayment.model.PaymentRequest;
import com.example.clientpayment.model.PaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {


    PaymentResponse createPayment(PaymentRequest paymentRequest);
    PaymentResponse updatePayment(PaymentRequest paymentRequest);
    Page<PaymentResponse> getAllPaymentsByClientId(String clientId, Pageable pageable);
    PaymentResponse getPaymentById(String paymentId);
    void deletePaymentById(String paymentId);
}
