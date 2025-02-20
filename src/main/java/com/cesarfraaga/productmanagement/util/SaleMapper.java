package com.cesarfraaga.productmanagement.util;

import com.cesarfraaga.productmanagement.dto.SaleDTO;
import com.cesarfraaga.productmanagement.entity.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ShoppingCartMapper.class, ClientMapper.class})
public interface SaleMapper {

    @Mapping(source = "shoppingCart", target = "shoppingCartDTO")
    @Mapping(source = "client", target = "clientDTO")
    SaleDTO toDTOSale(Sale sale);

    @Mapping(source = "shoppingCartDTO", target = "shoppingCart")
    @Mapping(source = "clientDTO", target = "client")
    Sale toEntitySale(SaleDTO dto);

}
