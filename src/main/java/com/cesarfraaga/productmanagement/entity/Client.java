package com.cesarfraaga.productmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client {

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String CPF;

    @Column(name = "birthdate")
    private String birthDate;

}
