package com.develop.estore.OrderService.sagas;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.develop.estore.OrderService.core.event.OrderApprovedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.develop.estore.OrderService.command.ApproveOrderCommand;
import com.develop.estore.OrderService.core.event.OrderCreatedEvent;

import core.command.ProgressPaymentCommand;
import core.command.ReserveProductCommand;
import core.dto.User;
import core.event.PaymentProcessedEvent;
import core.event.ProductReservedEvent;
import core.query.FetchUserPaymentDetailsQuery;
import lombok.extern.slf4j.Slf4j;

@Saga
@Slf4j
public class OrderSaga implements Serializable {

    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .orderId(orderCreatedEvent.getOrderId())
                .productId(orderCreatedEvent.getProductId())
                .quantity(orderCreatedEvent.getQuantity())
                .userId(orderCreatedEvent.getUserId())
                .build();

        log.info(
                "OrderCreatedEvent handled for orderId: {}",
                orderCreatedEvent.getOrderId() + " and productId: " + orderCreatedEvent.getProductId());

        commandGateway.send(reserveProductCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // handle compensating transaction
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent productReservedEvent) {
        // handle success
        log.info(
                "ProductReservedEvent is called for productId: {}",
                productReservedEvent.getProductId() + " and orderId: " + productReservedEvent.getOrderId());

        FetchUserPaymentDetailsQuery fetchUserPaymentDetailsQuery = FetchUserPaymentDetailsQuery.builder()
                .userId(productReservedEvent.getUserId())
                .build();

        User userPaymentDetails = null;
        try {
            userPaymentDetails =
                    queryGateway.query(fetchUserPaymentDetailsQuery, User.class).join();
        } catch (Exception e) {
            log.error(e.getMessage());
            // handle compensating transaction
            return;
        }

        if (userPaymentDetails == null) {
            // handle compensating transaction
            return;
        }
        log.info("User payment details are successfully fetched for userId: {}", userPaymentDetails.toString());

        ProgressPaymentCommand progressPaymentCommand = ProgressPaymentCommand.builder()
                .orderId(productReservedEvent.getOrderId())
                .paymentDetails(userPaymentDetails.getPaymentDetails())
                .paymentId(UUID.randomUUID().toString())
                .build();
        String result = null;
        try {
            result = commandGateway.sendAndWait(progressPaymentCommand, 10, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error(e.getMessage());
            // handle compensating transaction
        }
        if (result == null) {
            // handle compensating transaction
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(PaymentProcessedEvent paymentProcessedEvent) {
        ApproveOrderCommand approveOrderCommand = new ApproveOrderCommand(paymentProcessedEvent.getOrderId());
        commandGateway.send(approveOrderCommand);
        log.info("PaymentProcessedEvent:  Payment is processed for orderId: {}", paymentProcessedEvent.getOrderId());
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderApprovedEvent orderApprovedEvent) {
        // handle success
        log.info("OrderApprovedEvent: Order is approved for orderId: {}", orderApprovedEvent.getOrderId());
    }
}
