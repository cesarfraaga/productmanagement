package com.cesarfraaga.productmanagement.util;

import com.cesarfraaga.productmanagement.dto.ShoppingCartDTO;
import com.cesarfraaga.productmanagement.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface ShoppingCartMapper {

    @Mapping(source = "products", target = "productsDTO")
    ShoppingCartDTO toDTOShoppingCart(ShoppingCart shoppingCart);

    @Mapping(source = "productsDTO", target = "products")
    ShoppingCart toEntityShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
