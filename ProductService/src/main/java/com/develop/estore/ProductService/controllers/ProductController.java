package com.develop.estore.ProductService.controllers;

import com.develop.estore.ProductService.command.CreateProductCommand;
import com.develop.estore.ProductService.dto.request.CreateProductReq;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author admin
 */
@RestController
@RequestMapping("products")
public class ProductController {

    private final CommandGateway commandGateway;

    public ProductController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping
    public String getProducts() {
        return "Get all products";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id) {
        return "Get product with id: " + id;
    }

    @GetMapping("/create")
    public String createProduct(@RequestBody CreateProductReq productReq) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .title(productReq.getTitle())
                .quantity(productReq.getQuantity())
                .price(productReq.getPrice())
                .productId(UUID.randomUUID().toString())
                .build();
        String response;
        try {
            response = commandGateway.sendAndWait(createProductCommand);
        } catch (Exception e) {
            response = e.getLocalizedMessage();
        }
        return response;
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id) {
        return "Update product with id: " + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return "Delete product with id: " + id;
    }
}
