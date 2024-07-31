package com.develop.estore.OrderService.core.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.*;

import com.develop.estore.OrderService.core.enums.OrderStatus;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String orderId;

    private String userId;
    private String productId;
    private Integer quantity;
    private String addressId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
