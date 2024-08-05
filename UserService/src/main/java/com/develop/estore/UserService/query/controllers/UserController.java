package com.develop.estore.UserService.query.controllers;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import core.dto.User;
import core.query.FetchUserPaymentDetailsQuery;

@RestController
@RequestMapping("/users")
public class UserController {

    private final QueryGateway queryGateway;

    public UserController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{userId}/payment-details")
    public User getUserPaymentDetails(@PathVariable String userId) {
        FetchUserPaymentDetailsQuery query =
                FetchUserPaymentDetailsQuery.builder().userId(userId).build();
        return queryGateway.query(query, ResponseTypes.instanceOf(User.class)).join();
    }
}
