package com.develop.estore.OrderService.core.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateOrderReq {

    private String productId;
    private Integer quantity;
    private String addressId;
}
