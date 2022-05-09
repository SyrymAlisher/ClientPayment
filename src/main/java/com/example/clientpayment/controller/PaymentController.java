package com.example.clientpayment.controller;

import com.example.clientpayment.feign.ClientFeign;
import com.example.clientpayment.model.*;
import com.example.clientpayment.service.PaymentService;
import com.example.clientpayment.service.message.SendService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    private final ClientFeign clientFeign;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SendService sendService;

    public PaymentController(PaymentService paymentService, ClientFeign clientFeign) {
        this.paymentService = paymentService;
        this.clientFeign = clientFeign;
    }

    @GetMapping("/check")
    public String check() {
        return "client-payment is working";
    }

    @PostMapping
    public PaymentResponse createPayment(@RequestBody  PaymentRequest paymentRequest){
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

    @GetMapping("/details")
    public PaymentDetails getPaymentDetailsById(@RequestParam String paymentId) {
        PaymentResponse payment = paymentService.getPaymentById(paymentId);
        ClientResponse client = clientFeign.getClientById(payment.getClientId());
        return new PaymentDetails(paymentId, client, payment.getReceiptName(), payment.getCost());
    }

    @GetMapping("/all")
    public Page<PaymentResponse> getAllPayments(Pageable pageable){
        return paymentService.getAllPayments(pageable);
    }

    @GetMapping("/client")
    public Page<PaymentResponse> getAllPaymentsByClient(@RequestParam String clientId, Pageable pageable){
        return paymentService.getAllPaymentsByClientId(clientId, pageable);
    }

    @GetMapping("/clients")
    public List<ClientResponse> getAllClients(){
        return clientFeign.getAllClient();
    }

    @GetMapping("/total")
    public PaymentTotal getTotalOfClient(@RequestParam String clientId) {
        List<PaymentResponse> list = paymentService.getAllPaymentsListByClientId(clientId);
        double sum = 0;
        int cnt = 0;
        for(PaymentResponse l : list){
            sum += l.getCost();
            cnt++;
        }
        ClientResponse client = clientFeign.getClientById(clientId);
        return new PaymentTotal(client,cnt, sum);
    }

    @DeleteMapping
    public void deletePayment(@RequestParam String paymentId){
        paymentService.deletePaymentById(paymentId);
    }

    @PostMapping("/email")
    public EmailInfo sendClientData(@RequestParam String clientId) throws JsonProcessingException {
        List<PaymentResponse> list = paymentService.getAllPaymentsListByClientId(clientId);
        ClientResponse client = clientFeign.getClientById(clientId);

        HashMap<String, Double> payments = new HashMap<>();
        double sum = 0;
        int cnt = 0;
        for(PaymentResponse l : list){
            payments.put(l.getReceiptName(), l.getCost());
            sum += l.getCost();
            cnt++;
        }
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setId(UUID.randomUUID().toString());
        emailInfo.setClientEmail(client.getEmail());
        emailInfo.setClientName(client.getName());
        emailInfo.setClientSurname(client.getSurname());
        emailInfo.setPayments(payments);
        emailInfo.setTotalPayment(sum);
        sendService.send(objectMapper.writeValueAsString(emailInfo));
        return emailInfo;
    }

}
