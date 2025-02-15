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

        if (dto.getName() == null || dto.getName().isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty.");
        if (dto.getName().length() < 2)
            throw new IllegalArgumentException("Name is too short. The minimum length allowed is 2 characters.");
        if (dto.getName().length() > 50)
            throw new DataIntegrityViolationException("Name is too long. Maximum length allowed is 50 characters.");
/*        if (!(dto.getName() instanceof String))
            throw new IllegalArgumentException("Name must be a valid string.");*/

        if (dto.getDescription().isBlank())
            throw new IllegalArgumentException("Description cannot be null or empty.");
        if (dto.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Price must be greater than zero.");

        Product product = productMapper.toEntity(dto);
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }

    public ProductDTO update(ProductDTO dto) {
        if (dto.getId() == null || !productRepository.existsById(dto.getId()))
            throw new ResourceNotFoundException("Product not found with ID " + dto.getId() + ".");
        if (dto.getName().isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty.");
        if (dto.getDescription().isBlank())
            throw new IllegalArgumentException("Description cannot be null or empty.");
        if (dto.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Price must be greater than zero.");

        Product product = productMapper.toEntity(dto);
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }

    public ProductDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        Product product = productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product not found with ID " + id + "."));
        return productMapper.toDTO(product);
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id))
            throw new ResourceNotFoundException("Product not found with ID " + id + ".");
        productRepository.deleteById(id);
    }

    public List<ProductDTO> findAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (productList.isEmpty())
            throw new ResourceNotFoundException("No products available.");

        for (Product product : productList) {
            ProductDTO dto = productMapper.toDTO(product);
            productDTOList.add(dto);
        }
        return productDTOList;
    }

}
