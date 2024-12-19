package com.cesarfraaga.productmanagement.util;

import com.cesarfraaga.productmanagement.dto.StockDTO;
import com.cesarfraaga.productmanagement.entity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    Stock toEntityStock (StockDTO dto);
    StockDTO toDTOStock (Stock stock);

}
