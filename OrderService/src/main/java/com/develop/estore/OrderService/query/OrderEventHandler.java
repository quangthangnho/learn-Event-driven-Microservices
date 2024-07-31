package com.develop.estore.OrderService.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.develop.estore.OrderService.core.entity.OrderEntity;
import com.develop.estore.OrderService.core.event.OrderCreatedEvent;
import com.develop.estore.OrderService.core.repository.OrderRepository;

@Component
public class OrderEventHandler {

    private final OrderRepository orderRepository;

    public OrderEventHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        orderRepository.save(new OrderEntity(
                orderCreatedEvent.getOrderId(),
                orderCreatedEvent.getUserId(),
                orderCreatedEvent.getProductId(),
                orderCreatedEvent.getQuantity(),
                orderCreatedEvent.getAddressId(),
                orderCreatedEvent.getOrderStatus()));
    }
}
