package com.cesarfraaga.productmanagement.repository;

import com.cesarfraaga.productmanagement.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
