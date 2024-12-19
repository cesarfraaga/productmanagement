package com.cesarfraaga.productmanagement.dto;

import com.cesarfraaga.productmanagement.entity.Product;
import lombok.Data;

@Data
public class StockDTO {

    private Long id;
    private Product product;
}
