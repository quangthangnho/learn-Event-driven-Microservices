package com.develop.estore.ProductService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
@RequestMapping("products")
public class ProductController {

    // generate the CRUD operations here
    @GetMapping
    public String getProducts() {
        return "Get all products";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Long id) {
        return "Get product with id: " + id;
    }

    @GetMapping("/create")
    public String createProduct() {
        return "Create product";
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
