package com.develop.estore.ProductService.query.dto.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResDto {
    private String productId;
    private String title;
    private Integer quantity;
    private BigDecimal price;
}
