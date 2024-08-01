package com.develop.estore.OrderService.sagas;

import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import com.develop.estore.OrderService.core.event.OrderCreatedEvent;

import core.command.ReserveProductCommand;
import core.event.ProductReservedEvent;
import org.springframework.stereotype.Component;

@Saga
@Slf4j
@Component
public class OrderSaga implements Serializable {

    private final transient CommandGateway commandGateway;

    public OrderSaga(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent orderCreatedEvent) {
        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .orderId(orderCreatedEvent.getOrderId())
                .productId(orderCreatedEvent.getProductId())
                .quantity(orderCreatedEvent.getQuantity())
                .userId(orderCreatedEvent.getUserId())
                .build();

        log.info("OrderCreatedEvent handled for orderId: {}", orderCreatedEvent.getOrderId() + " and productId: " + orderCreatedEvent.getProductId());

        commandGateway.send(reserveProductCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // handle compensating transaction
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent productReservedEvent) {
        // handle success
        log.info("ProductReservedEvent is called for productId: {}", productReservedEvent.getProductId() + " and orderId: " + productReservedEvent.getOrderId());
    }
}
