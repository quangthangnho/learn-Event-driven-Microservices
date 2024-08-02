package com.develop.estore.OrderService.core.event;

import com.develop.estore.OrderService.core.enums.OrderStatus;

import lombok.Value;

/**
 * @author admin
 */
@Value
public class OrderApprovedEvent {
    String orderId;
    OrderStatus orderStatus = OrderStatus.APPROVED;
}
