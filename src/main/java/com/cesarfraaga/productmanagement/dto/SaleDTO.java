package com.cesarfraaga.productmanagement.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO {

    private Long id;
    private Long clientId;
    private Long sellerId;
    private Long shoppingCartId;
    private List<Long> productsIds;
    private LocalDateTime salesDate;

}
