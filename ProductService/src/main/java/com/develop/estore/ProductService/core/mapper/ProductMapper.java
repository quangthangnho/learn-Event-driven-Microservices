package com.develop.estore.ProductService.core.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.develop.estore.ProductService.core.data.entity.ProductEntity;
import com.develop.estore.ProductService.query.dto.response.ProductResDto;

/**
 * @author admin
 */
@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    List<ProductResDto> toProductsResDto(List<ProductEntity> productEntity);
}
