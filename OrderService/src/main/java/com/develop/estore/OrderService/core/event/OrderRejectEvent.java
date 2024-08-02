package com.develop.estore.OrderService.core.event;

import com.develop.estore.OrderService.core.enums.OrderStatus;

import lombok.Value;

@Value
public class OrderRejectEvent {
    String orderId;
    String reason;
    OrderStatus orderStatus = OrderStatus.REJECTED;
}
