package com.develop.estore.OrderService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RejectOrderCommand {

    @TargetAggregateIdentifier
    private String orderId;

    private String reason;
}
