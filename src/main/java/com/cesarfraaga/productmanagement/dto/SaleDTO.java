package com.cesarfraaga.productmanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO { // Testar JSON Property Snake Case

    private Long id;
    private Long clientId;
    private Long shoppingCartId;
    private LocalDateTime salesDate;

}
