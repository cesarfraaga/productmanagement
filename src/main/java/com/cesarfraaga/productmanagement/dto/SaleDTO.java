package com.cesarfraaga.productmanagement.dto;

import com.cesarfraaga.productmanagement.validator.Validatable;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDTO implements Validatable { // Testar JSON Property Snake Case

    private Long id;
    private ClientDTO clientDTO;
    private ShoppingCartDTO shoppingCartDTO;

}
