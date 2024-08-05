package com.develop.estore.OrderService.command.controllers;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.estore.OrderService.command.CreateOrdersCommand;
import com.develop.estore.OrderService.core.dto.request.CreateOrderReq;
import com.develop.estore.OrderService.core.dto.response.OrderSumaryDto;
import com.develop.estore.OrderService.core.enums.OrderStatus;
import com.develop.estore.OrderService.query.FindOrderQuery;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public OrdersController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/create")
    public OrderSumaryDto createProduct(@RequestBody CreateOrderReq createOrderReq) {
        CreateOrdersCommand createProductCommand = CreateOrdersCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .productId(createOrderReq.getProductId())
                .quantity(createOrderReq.getQuantity())
                .addressId(createOrderReq.getAddressId())
                .orderStatus(OrderStatus.CREATED)
                .build();

        try (SubscriptionQueryResult<OrderSumaryDto, OrderSumaryDto> queryResult = queryGateway.subscriptionQuery(
                new FindOrderQuery(createProductCommand.getOrderId()),
                ResponseTypes.instanceOf(OrderSumaryDto.class),
                ResponseTypes.instanceOf(OrderSumaryDto.class))) {
            commandGateway.sendAndWait(createProductCommand);
            return queryResult.updates().blockFirst();
        }
    }
}
