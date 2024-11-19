package com.cesarfraaga.productmanagement.repository;

import com.cesarfraaga.productmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
