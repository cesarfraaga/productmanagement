package com.cesarfraaga.productmanagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private List<Long> productsIds;

}
