package com.develop.estore.ProductService.command;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.develop.estore.ProductService.core.data.entity.ProductLookupEntity;
import com.develop.estore.ProductService.core.data.repository.ProductLookupRepository;
import com.develop.estore.ProductService.core.events.ProductCreatedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

    private final ProductLookupRepository productLookupRepository;

    public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) throws Exception {
        productLookupRepository.save(
                new ProductLookupEntity(productCreatedEvent.getProductId(), productCreatedEvent.getTitle()));
        if(true) throw new Exception("Simulating exception in ProductLookupEventsHandler");
    }
}
