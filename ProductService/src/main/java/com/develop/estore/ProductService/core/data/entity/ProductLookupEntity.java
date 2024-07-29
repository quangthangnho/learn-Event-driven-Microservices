package com.develop.estore.ProductService.core.data.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.*;

/**
 * @author admin
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductLookupEntity implements Serializable {

    @Id
    @Column(unique = true)
    private String productId;

    @Column(unique = true)
    private String title;
}
