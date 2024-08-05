package com.develop.estore.PaymentService.query;

import org.springframework.stereotype.Component;

import com.develop.estore.PaymentService.core.repository.PaymentRepository;

@Component
public class PaymentQueryHandler {

    private final PaymentRepository paymentRepository;

    public PaymentQueryHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}
