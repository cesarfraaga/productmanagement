package com.cesarfraaga.productmanagement.util;

import com.cesarfraaga.productmanagement.dto.SaleDTO;
import com.cesarfraaga.productmanagement.entity.Sale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    Sale toEntitySale(SaleDTO dto);
    SaleDTO toDTOSale(Sale sale);
}
