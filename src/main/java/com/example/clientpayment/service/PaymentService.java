package com.example.clientpayment.service;

import com.example.clientpayment.model.PaymentRequest;
import com.example.clientpayment.model.PaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaymentService {


    PaymentResponse createPayment(PaymentRequest paymentRequest);
    PaymentResponse updatePayment(PaymentRequest paymentRequest);
    Page<PaymentResponse> getAllPaymentsByClientId(String clientId, Pageable pageable);
    List<PaymentResponse> getAllPaymentsListByClientId(String clientId);

    Page<PaymentResponse> getAllPayments(Pageable pageable);
    PaymentResponse getPaymentById(String paymentId);
    void deletePaymentById(String paymentId);
}
