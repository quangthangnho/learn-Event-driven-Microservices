package com.develop.estore.PaymentService.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.develop.estore.PaymentService.core.entity.PaymentEntity;
import com.develop.estore.PaymentService.core.repository.PaymentRepository;

import core.event.PaymentProcessedEvent;

@Component
public class PaymentEventsHandler {

    private final PaymentRepository paymentRepository;

    public PaymentEventsHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        PaymentEntity paymentEntity =
                new PaymentEntity(paymentProcessedEvent.getPaymentId(), paymentProcessedEvent.getOrderId());
        paymentRepository.save(paymentEntity);
    }
}
