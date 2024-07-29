package com.develop.estore.ProductService.core.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.develop.estore.ProductService.core.data.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    ProductEntity findByProductId(String productId);

    ProductEntity findByProductIdOrTitle(String productId, String title);
}
