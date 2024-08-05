package com.develop.estore.OrderService.core.dto.response;

import com.develop.estore.OrderService.core.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSumaryDto {

    private String orderId;
    private OrderStatus orderStatus;
    private String message;

    public OrderSumaryDto(String orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
