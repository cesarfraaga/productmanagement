package com.cesarfraaga.productmanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDTO {

    private Long id;
    private List<ProductDTO> productsDTO;
}
