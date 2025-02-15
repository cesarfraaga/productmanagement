package com.cesarfraaga.productmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "shopping_cart_product", //Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "shopping_cart_id"), //Chave estrangeira para ShoppingCart
            inverseJoinColumns = @JoinColumn(name = "product_id") //Chave estrangeira para Product
    )
    private List<Product> products = new ArrayList<>();

    @OneToOne(mappedBy = "shoppingCart")
    private Sale sale;
}