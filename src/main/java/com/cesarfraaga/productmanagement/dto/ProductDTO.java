package com.cesarfraaga.productmanagement.dto;

import com.cesarfraaga.productmanagement.util.validator.Validatable;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO implements Validatable {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer quantity;
}
