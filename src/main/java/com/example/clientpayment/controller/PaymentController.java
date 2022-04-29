package com.example.clientpayment.controller;

import com.example.clientpayment.model.PaymentRequest;
import com.example.clientpayment.model.PaymentResponse;
import com.example.clientpayment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/check")
    public String check() {
        return "client-payment is working";
    }

    @PostMapping
    public PaymentResponse createPayment(@RequestParam String paymentId, @RequestBody  PaymentRequest paymentRequest){
        return paymentService.createPayment(paymentRequest);
    }

    @PutMapping
    public PaymentResponse updatePayment(@RequestParam String paymentId, @RequestBody PaymentRequest paymentRequest){
        paymentRequest.setPaymentId(paymentId);
        return paymentService.updatePayment(paymentRequest);
    }

    @GetMapping
    public PaymentResponse getPaymentById(@RequestParam String paymentId){
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/client")
    public Page<PaymentResponse> getAllPaymentsByClient(@RequestParam String clientId, Pageable pageable){
        return paymentService.getAllPaymentsByClientId(clientId, pageable);
    }

    @DeleteMapping
    public void deletePayment(@RequestParam String paymentId){
        paymentService.deletePaymentById(paymentId);
    }

}
