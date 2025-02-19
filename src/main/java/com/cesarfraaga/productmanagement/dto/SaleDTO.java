package com.cesarfraaga.productmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDTO { // Testar JSON Property Snake Case

    private Long id;
    private ClientDTO clientDTO;
    private ShoppingCartDTO shoppingCartDTO;

}
