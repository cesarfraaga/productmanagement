package com.cesarfraaga.productmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Client client;

    private Seller seller;

    private ShoppingCart shoppingCart;

    private List<Product> products = new ArrayList<>();

    private LocalDateTime salesDate;



}
