package com.example.clientpayment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRespotitory extends ElasticsearchRepository<PaymentEntity, String> {

    Page<PaymentEntity> getPaymentEntitiesByType(String type, Pageable pageable);

    PaymentEntity getPaymentEntityByPaymentId(String taskId);

    Page<PaymentEntity> getPaymentEntitiesByClientId(String clientId, Pageable pageable);

    PaymentEntity deletePaymentEntityByPaymentId(String paymentId);


}
