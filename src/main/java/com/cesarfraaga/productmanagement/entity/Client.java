package com.cesarfraaga.productmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "client_name")
    private String name;

    @Column(name = "client_cpf")
    private String CPF;

    @Column(name = "client_birthday")
    private String birthDay;

}
