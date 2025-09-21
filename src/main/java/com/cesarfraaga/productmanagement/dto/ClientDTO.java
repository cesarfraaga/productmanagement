package com.cesarfraaga.productmanagement.dto;

import com.cesarfraaga.productmanagement.validator.Validatable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ClientDTO implements Validatable {
    private Long id;
    private String name;
    private String cpf;
    private String birthDay;
    private List<SaleDTO> sales;
}
