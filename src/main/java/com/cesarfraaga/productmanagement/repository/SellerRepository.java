package com.cesarfraaga.productmanagement.repository;

import com.cesarfraaga.productmanagement.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
