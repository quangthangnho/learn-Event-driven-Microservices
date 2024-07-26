package com.develop.estore.ProductService.query.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductResDto {
    private String productId;
    private String title;
    private Integer quantity;
    private BigDecimal price;
}
