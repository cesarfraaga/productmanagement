package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.ProductDTO;
import com.cesarfraaga.productmanagement.entity.Product;
import com.cesarfraaga.productmanagement.repository.ProductRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;

    public Product save(ProductDTO product) {
        if (product.getName() == null || product.getPrice() == null)
            System.out.println("Informações insuficientes.");
        if (product.getPrice() < 0)
            System.out.println("O valor do produto tem que ser maior que 0");

        // Criar um mapper para converter ProductDTO em Product e vice-versa
        return null;
        //return repository.save(product);
    }

    public Product update(Product product) {
        if (repository.existsById(product.getId())) {
            return repository.save(product);
        } else {
            throw new ResourceNotFoundException("Não existe um produto com ID " + product.getId() + ".");
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

}
