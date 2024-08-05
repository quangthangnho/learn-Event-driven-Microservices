package com.develop.estore.PaymentService.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.develop.estore.PaymentService.core.entity.PaymentEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {
    PaymentEntity findByOrderId(String orderId);
}
