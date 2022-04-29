package com.example.clientpayment.service;

import com.example.clientpayment.model.PaymentRequest;
import com.example.clientpayment.model.PaymentResponse;
import com.example.clientpayment.repository.PaymentEntity;
import com.example.clientpayment.repository.PaymentRespotitory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    private PaymentRespotitory paymentRespotitory;

    static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        paymentRequest.setPaymentId(UUID.randomUUID().toString());
        PaymentEntity paymentEntity = modelMapper.map(paymentRequest, PaymentEntity.class);
        paymentEntity = paymentRespotitory.save(paymentEntity);
        return modelMapper.map(paymentEntity, PaymentResponse.class);
    }

    @Override
    public PaymentResponse updatePayment(PaymentRequest paymentRequest) {

        PaymentEntity paymentEntity = modelMapper.map(paymentRequest, PaymentEntity.class);
        PaymentEntity dbEntity = paymentRespotitory.getPaymentEntityByPaymentId(paymentRequest.getPaymentId());
        paymentEntity.setPaymentId(dbEntity.getPaymentId());
        paymentEntity = paymentRespotitory.save(paymentEntity);
        return modelMapper.map(paymentEntity, PaymentResponse.class);
    }

    @Override
    public Page<PaymentResponse> getAllPaymentsByClientId(String clientId, Pageable pageable) {

        return paymentRespotitory.getPaymentEntitiesByClientId(clientId, pageable).
                map(payment -> modelMapper.map(payment, PaymentResponse.class));
    }

    @Override
    public PaymentResponse getPaymentById(String paymentId) {

        PaymentEntity paymentEntity = paymentRespotitory.getPaymentEntityByPaymentId(paymentId);
        return modelMapper.map(paymentEntity, PaymentResponse.class);
    }

    @Override
    public void deletePaymentById(String paymentId) {
        paymentRespotitory.deletePaymentEntityByPaymentId(paymentId);

    }
}
