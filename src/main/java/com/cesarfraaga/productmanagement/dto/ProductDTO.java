package com.cesarfraaga.productmanagement.dto;

import com.cesarfraaga.productmanagement.validator.Validatable;
import jakarta.persistence.Column;
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
