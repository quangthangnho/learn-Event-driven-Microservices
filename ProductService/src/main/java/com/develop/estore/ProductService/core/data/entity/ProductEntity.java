package com.develop.estore.ProductService.core.data.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true)
    private String productId;

    @Column(unique = true)
    private String title;

    private Integer quantity;
    private BigDecimal price;

    @Override
    public String toString() {
        return "ProductEntity{" + "productId='"
                + productId + '\'' + ", title='"
                + title + '\'' + ", quantity="
                + quantity + ", price="
                + price + '}';
    }
}
