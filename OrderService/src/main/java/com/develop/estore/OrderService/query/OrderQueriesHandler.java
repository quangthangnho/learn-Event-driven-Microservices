package com.develop.estore.OrderService.query;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.develop.estore.OrderService.core.dto.response.OrderSumaryDto;
import com.develop.estore.OrderService.core.entity.OrderEntity;
import com.develop.estore.OrderService.core.repository.OrderRepository;

@Component
public class OrderQueriesHandler {

    private final OrderRepository orderRepository;

    public OrderQueriesHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @QueryHandler
    public OrderSumaryDto findOrder(FindOrderQuery findOrderQuery) {
        OrderEntity orderEntity = orderRepository.findByOrderId(findOrderQuery.getOrderId());
        return new OrderSumaryDto(orderEntity.getOrderId(), orderEntity.getOrderStatus());
    }
}
