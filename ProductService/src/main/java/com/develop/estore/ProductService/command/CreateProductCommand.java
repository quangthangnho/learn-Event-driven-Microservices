package com.develop.estore.ProductService.command;

import java.math.BigDecimal;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private final String productId;

    private final String title;
    private final Integer quantity;
    private final BigDecimal price;
}
