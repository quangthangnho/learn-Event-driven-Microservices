package com.develop.estore.ProductService.query.controllers;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.estore.ProductService.query.FindProductQuery;
import com.develop.estore.ProductService.query.dto.response.ProductResDto;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductResDto> getProducts() {
        FindProductQuery findProductQuery = new FindProductQuery();
        return queryGateway
                .query(findProductQuery, ResponseTypes.multipleInstancesOf(ProductResDto.class))
                .join();
    }
}
