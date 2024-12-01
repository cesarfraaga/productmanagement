package com.cesarfraaga.productmanagement.util;

import com.cesarfraaga.productmanagement.dto.SellerDTO;
import com.cesarfraaga.productmanagement.entity.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerMapper {

    Seller toEntitySeller(SellerDTO sellerDTO);
    SellerDTO toDTOSeller(Seller seller);
}
