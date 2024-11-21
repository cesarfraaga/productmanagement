package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.ProductDTO;
import com.cesarfraaga.productmanagement.entity.Product;
import com.cesarfraaga.productmanagement.repository.ProductRepository;
import com.cesarfraaga.productmanagement.util.ProductMapper;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDTO save(ProductDTO dto) {

        Product product = productMapper.toEntity(dto);
        product = productRepository.save(product);
        return productMapper.toDTO(product);

        /*if (product.getName() == null || product.getPrice() == null)
            System.out.println("Informações insuficientes.");
        if (product.getPrice() < 0)
            System.out.println("O valor do produto tem que ser maior que 0");
        return repository.save(product);*/
    }

    public Product update(Product product) {
        if (productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        } else {
            throw new ResourceNotFoundException("Não existe um produto com ID " + product.getId() + ".");
        }
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not Found"));
        return productMapper.toDTO(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
