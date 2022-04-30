package com.example.clientpayment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends ElasticsearchRepository<PaymentEntity, String> {

    Page<PaymentEntity> getPaymentEntitiesBy(Pageable pageable);
    PaymentEntity getPaymentEntityByPaymentId(String paymentId);

    Page<PaymentEntity> getPaymentEntitiesByClientId(String clientId, Pageable pageable);
    List<PaymentEntity> getPaymentEntitiesByClientId(String clientId);
    PaymentEntity deletePaymentEntityByPaymentId(String paymentId);


}
