package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.ProductDTO;
import com.cesarfraaga.productmanagement.entity.Product;
import com.cesarfraaga.productmanagement.exception.ResourceNotFoundException;
import com.cesarfraaga.productmanagement.repository.ProductRepository;
import com.cesarfraaga.productmanagement.util.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    // Próximo passo: Criar a própria classe de exceção;
}
