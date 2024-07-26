package com.develop.estore.ProductService.command.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductReq {

    private String title;
    private Integer quantity;
    private BigDecimal price;
}
