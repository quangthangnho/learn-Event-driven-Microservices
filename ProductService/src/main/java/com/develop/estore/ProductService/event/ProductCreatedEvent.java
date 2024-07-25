package com.develop.estore.ProductService.event;

import com.develop.estore.ProductService.command.CreateProductCommand;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreatedEvent {

    private String productId;
    private String title;
    private Integer quantity;
    private BigDecimal price;

    public ProductCreatedEvent(CreateProductCommand createProductCommand) {
        this.productId = createProductCommand.getProductId();
        this.title = createProductCommand.getTitle();
        this.quantity = createProductCommand.getQuantity();
        this.price = createProductCommand.getPrice();
    }
}
