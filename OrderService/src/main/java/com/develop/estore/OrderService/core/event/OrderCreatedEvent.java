package com.develop.estore.OrderService.core.event;

import com.develop.estore.OrderService.command.CreateOrdersCommand;
import com.develop.estore.OrderService.core.enums.OrderStatus;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {

    private String orderId;
    private String userId;
    private String productId;
    private Integer quantity;
    private String addressId;
    private OrderStatus orderStatus;

    public OrderCreatedEvent(CreateOrdersCommand createOrdersCommand) {
        this.orderId = createOrdersCommand.getOrderId();
        this.userId = createOrdersCommand.getUserId();
        this.productId = createOrdersCommand.getProductId();
        this.quantity = createOrdersCommand.getQuantity();
        this.addressId = createOrdersCommand.getAddressId();
        this.orderStatus = createOrdersCommand.getOrderStatus();
    }
}
