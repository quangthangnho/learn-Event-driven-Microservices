package com.develop.estore.OrderService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.develop.estore.OrderService.core.enums.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrdersCommand {

    @TargetAggregateIdentifier
    private String orderId;

    private String userId;
    private String productId;
    private Integer quantity;
    private String addressId;
    private OrderStatus orderStatus;
}
