package com.cesarfraaga.productmanagement.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private Long id;
    private String name;
    private String CPF;
    private String birthDay;
}
