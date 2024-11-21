package com.cesarfraaga.productmanagement.util;

import com.cesarfraaga.productmanagement.dto.ProductDTO;
import com.cesarfraaga.productmanagement.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO dto);
}
