package com.cesarfraaga.productmanagement.dto;

import lombok.Data;

@Data
public class SellerDTO {
    private Long id;
    private String name;
    private String cpf;
    private String password;
    private String birthDay;
}
