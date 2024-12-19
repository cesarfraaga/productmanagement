package com.cesarfraaga.productmanagement.repository;

import com.cesarfraaga.productmanagement.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
