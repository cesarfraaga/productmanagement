package com.cesarfraaga.productmanagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {  //TODO implements Validatable here
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer quantity;
}
