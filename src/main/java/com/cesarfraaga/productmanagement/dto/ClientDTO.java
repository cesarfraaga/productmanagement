package com.cesarfraaga.productmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String CPF;
    private String birthDay;
}
