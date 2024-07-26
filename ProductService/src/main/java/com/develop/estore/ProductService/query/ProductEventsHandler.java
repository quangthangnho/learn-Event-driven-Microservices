package com.develop.estore.ProductService.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.develop.estore.ProductService.core.data.entity.ProductEntity;
import com.develop.estore.ProductService.core.events.ProductCreatedEvent;
import com.develop.estore.ProductService.core.data.repository.ProductRepository;

@Component
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductEntity productEntity = new ProductEntity(
                productCreatedEvent.getProductId(),
                productCreatedEvent.getTitle(),
                productCreatedEvent.getQuantity(),
                productCreatedEvent.getPrice());
        productRepository.save(productEntity);
    }
}
