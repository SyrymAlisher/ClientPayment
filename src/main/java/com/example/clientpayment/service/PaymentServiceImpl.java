package com.example.clientpayment.service;

import com.example.clientpayment.model.PaymentRequest;
import com.example.clientpayment.model.PaymentResponse;
import com.example.clientpayment.repository.PaymentEntity;
import com.example.clientpayment.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    private PaymentRepository paymentRepository;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        paymentRequest.setPaymentId(UUID.randomUUID().toString());
        PaymentEntity paymentEntity = modelMapper.map(paymentRequest, PaymentEntity.class);
        paymentEntity = paymentRepository.save(paymentEntity);
        return modelMapper.map(paymentEntity, PaymentResponse.class);
    }

    @Override
    public PaymentResponse updatePayment(PaymentRequest paymentRequest) {

        PaymentEntity paymentEntity = modelMapper.map(paymentRequest, PaymentEntity.class);
        PaymentEntity dbEntity = paymentRepository.getPaymentEntityByPaymentId(paymentRequest.getPaymentId());
        paymentEntity.setPaymentId(dbEntity.getPaymentId());
        paymentEntity = paymentRepository.save(paymentEntity);
        return modelMapper.map(paymentEntity, PaymentResponse.class);
    }

    @Override
    public Page<PaymentResponse> getAllPaymentsByClientId(String clientId, Pageable pageable) {

        return paymentRepository.getPaymentEntitiesByClientId(clientId, pageable).
                map(payment -> modelMapper.map(payment, PaymentResponse.class));
    }

    @Override
    public List<PaymentResponse> getAllPaymentsListByClientId(String clientId) {
        return paymentRepository.getPaymentEntitiesByClientId(clientId).stream()
                .map(payment -> modelMapper.map(payment, PaymentResponse.class))
                .collect(Collectors.toList());
    }


    @Override
    public Page<PaymentResponse> getAllPayments(Pageable pageable) {
        return paymentRepository.getPaymentEntitiesBy(pageable).
           map(payment -> modelMapper.map(payment, PaymentResponse.class));
    }

    @Override
    public PaymentResponse getPaymentById(String paymentId) {

        PaymentEntity paymentEntity = paymentRepository.getPaymentEntityByPaymentId(paymentId);
        return modelMapper.map(paymentEntity, PaymentResponse.class);
    }

    @Override
    public void deletePaymentById(String paymentId) {
        paymentRepository.deletePaymentEntityByPaymentId(paymentId);
    }
}
