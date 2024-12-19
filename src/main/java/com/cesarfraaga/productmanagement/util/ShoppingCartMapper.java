package com.cesarfraaga.productmanagement.util;

import com.cesarfraaga.productmanagement.dto.ShoppingCartDTO;
import com.cesarfraaga.productmanagement.entity.ShoppingCart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {

    ShoppingCart toEntityShoppingCart(ShoppingCartDTO dto);
    ShoppingCartDTO toDTOShoppingCart(ShoppingCart shoppingCart);

}
