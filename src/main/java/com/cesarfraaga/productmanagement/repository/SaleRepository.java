package com.cesarfraaga.productmanagement.repository;

import com.cesarfraaga.productmanagement.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
