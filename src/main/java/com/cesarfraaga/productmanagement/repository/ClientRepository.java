package com.cesarfraaga.productmanagement.repository;

import com.cesarfraaga.productmanagement.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
