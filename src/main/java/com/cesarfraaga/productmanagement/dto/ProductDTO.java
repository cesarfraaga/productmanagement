package com.cesarfraaga.productmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
}
