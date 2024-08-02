package com.develop.estore.PaymentService.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import core.command.ProgressPaymentCommand;
import core.dto.PaymentDetails;
import core.event.PaymentProcessedEvent;
import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;

    private String orderId;
    private PaymentDetails paymentDetails;

    @CommandHandler
    public PaymentAggregate(ProgressPaymentCommand progressPaymentCommand) {
        if (progressPaymentCommand.getOrderId() == null || progressPaymentCommand.getPaymentId() == null) {
            throw new IllegalArgumentException("Order Id or Payment Id cannot be null");
        }
        PaymentProcessedEvent paymentProcessedEvent = PaymentProcessedEvent.builder()
                .paymentId(progressPaymentCommand.getPaymentId())
                .orderId(progressPaymentCommand.getOrderId())
                .build();
        AggregateLifecycle.apply(paymentProcessedEvent);
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        this.paymentId = paymentProcessedEvent.getPaymentId();
        this.orderId = paymentProcessedEvent.getOrderId();
    }
}
