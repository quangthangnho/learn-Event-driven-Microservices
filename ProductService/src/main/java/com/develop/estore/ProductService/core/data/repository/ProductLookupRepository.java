package com.develop.estore.ProductService.core.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.develop.estore.ProductService.core.data.entity.ProductLookupEntity;

@Repository
public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {

    ProductLookupEntity findByProductIdOrTitle(String productId, String title);
}
