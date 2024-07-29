package com.develop.estore.ProductService.query;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.develop.estore.ProductService.core.data.entity.ProductEntity;
import com.develop.estore.ProductService.core.data.repository.ProductRepository;
import com.develop.estore.ProductService.core.mapper.ProductMapper;
import com.develop.estore.ProductService.query.dto.response.ProductResDto;

@Component
public class ProductQueryHandler {

    private final ProductRepository productRepository;

    public ProductQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductResDto> findProducts(FindProductQuery findProductQuery) {
        List<ProductEntity> productEntities = productRepository.findAll();
        return ProductMapper.INSTANCE.toProductsResDto(productEntities);
    }
}
