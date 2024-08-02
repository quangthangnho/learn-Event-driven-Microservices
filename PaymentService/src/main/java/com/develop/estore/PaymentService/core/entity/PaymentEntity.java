package com.develop.estore.PaymentService.core.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity implements Serializable {

    @Id
    private String paymentId;

    @Column
    public String orderId;
}
