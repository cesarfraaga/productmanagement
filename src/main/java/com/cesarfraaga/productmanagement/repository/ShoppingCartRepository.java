package com.cesarfraaga.productmanagement.repository;

import com.cesarfraaga.productmanagement.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("SELECT sc FROM ShoppingCart sc LEFT JOIN FETCH sc.products WHERE sc.id = :id")
    Optional<ShoppingCart> findByIdWithProducts(@Param("id") Long id);

}
