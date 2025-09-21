package com.cesarfraaga.productmanagement.dto;

import com.cesarfraaga.productmanagement.util.validator.Validatable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShoppingCartDTO  implements Validatable {
    private Long id;
    private List<ProductDTO> productsDTO;

    @JsonIgnore
    public List<Long> getProductsIds() {
        return productsDTO.stream()
                .map(ProductDTO::getId)
                .toList();
    }

}

