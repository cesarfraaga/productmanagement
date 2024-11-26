package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.ProductDTO;
import com.cesarfraaga.productmanagement.entity.Product;
import com.cesarfraaga.productmanagement.exception.ResourceNotFoundException;
import com.cesarfraaga.productmanagement.repository.ProductRepository;
import com.cesarfraaga.productmanagement.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDTO save(ProductDTO dto) {

        if (dto.getName().isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty.");
        if (dto.getDescription().isBlank())
            throw new IllegalArgumentException("Description cannot be null or empty.");
        if (dto.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Price must be greater than zero.");
        try {
            Product product = productMapper.toEntity(dto);
            product = productRepository.save(product);
            return productMapper.toDTO(product);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException("A product with this name already exists.");
        }
    }

    public ProductDTO update(ProductDTO dto) {
        if (dto.getId() == null || !productRepository.existsById(dto.getId()))
            throw new ResourceNotFoundException("Product not found with ID " + dto.getId() + ".");
        Product product = productMapper.toEntity(dto);
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product not found with ID " + id + "."));
        return productMapper.toDTO(product);
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID " + id + ".");
        }
        productRepository.deleteById(id);
    }

    public List<ProductDTO> findAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productList) {
            ProductDTO dto = productMapper.toDTO(product);
            productDTOList.add(dto);
        }
        return productDTOList;
    }

}
