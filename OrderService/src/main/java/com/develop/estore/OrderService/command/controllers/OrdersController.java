package com.develop.estore.OrderService.command.controllers;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.estore.OrderService.command.CreateOrdersCommand;
import com.develop.estore.OrderService.core.dto.request.CreateOrderReq;
import com.develop.estore.OrderService.core.enums.OrderStatus;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final CommandGateway commandGateway;

    public OrdersController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public String createProduct(@RequestBody CreateOrderReq createOrderReq) {
        CreateOrdersCommand createProductCommand = CreateOrdersCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .productId(createOrderReq.getProductId())
                .quantity(createOrderReq.getQuantity())
                .addressId(createOrderReq.getAddressId())
                .orderStatus(OrderStatus.CREATED)
                .build();
        return commandGateway.sendAndWait(createProductCommand);
    }
}
