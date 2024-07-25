package com.develop.estore.ProductService.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductReq {

    private String title;
    private Integer quantity;
    private BigDecimal price;
}
