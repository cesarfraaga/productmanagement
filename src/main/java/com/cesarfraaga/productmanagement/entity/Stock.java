package com.cesarfraaga.productmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long productId;

    // Preciso indicar a quantidade por produto

}
