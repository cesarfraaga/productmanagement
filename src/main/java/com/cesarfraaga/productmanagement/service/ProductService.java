package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.ProductDTO;
import com.cesarfraaga.productmanagement.entity.Product;
import com.cesarfraaga.productmanagement.exception.ResourceNotFoundException;
import com.cesarfraaga.productmanagement.repository.ProductRepository;
import com.cesarfraaga.productmanagement.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.cesarfraaga.productmanagement.util.ExceptionConstants.*;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDTO save(ProductDTO dto) {

        validateBeforeSaveOrUpdate(dto);

        Product product = productMapper.toEntity(dto);
        return saveAndReturnDTO(product);
    }

    public ProductDTO update(ProductDTO dto) {
        if (dto.getId() == null || !productRepository.existsById(dto.getId()))
            throw new ResourceNotFoundException(PRODUCT_ID_NOT_FOUND_MESSAGE + dto.getId() + PERIOD);

        validateBeforeSaveOrUpdate(dto);

        Product product = productMapper.toEntity(dto);
        return saveAndReturnDTO(product);
    }

    public ProductDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(PRODUCT_ID_NOT_NULL_MESSAGE);
        }
        Product product = productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(PRODUCT_ID_NOT_FOUND_MESSAGE + id + PERIOD));
        return productMapper.toDTO(product);
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id))
            throw new ResourceNotFoundException(PRODUCT_ID_NOT_FOUND_MESSAGE + id + PERIOD);
        productRepository.deleteById(id);
    }

    public List<ProductDTO> findAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (productList.isEmpty())
            throw new ResourceNotFoundException(PRODUCT_NO_AVAILABLE_MESSAGE);

        for (Product product : productList) {
            ProductDTO dto = productMapper.toDTO(product);
            productDTOList.add(dto);
        }
        return productDTOList;
    }

    private void validateBeforeSaveOrUpdate(ProductDTO dto) {

        int maxLengthClientName = 50;
        int minLengthProductName = 2;

        if (dto.getName() == null || dto.getName().isBlank())
            throw new IllegalArgumentException(PRODUCT_NAME_NULL_OR_EMPTY_MESSAGE);
        if (dto.getName().length() < minLengthProductName || dto.getName().length() > maxLengthClientName)
            throw new IllegalArgumentException(PRODUCT_NAME_LENGTH_MESSAGE);
        if (!dto.getName().matches(BASIC_CHARACTER))
            throw new IllegalArgumentException(PRODUCT_NAME_NOT_SPECIAL_CHARACTER_MESSAGE);

        if (dto.getDescription().isBlank())
            throw new IllegalArgumentException(PRODUCT_DESCRIPTION_NULL_OR_EMPTY_MESSAGE);
        if (!dto.getDescription().matches(BASIC_CHARACTER))
            throw new IllegalArgumentException(PRODUCT_DESCRIPTION_NOT_SPECIAL_CHARACTER_MESSAGE);

        if (dto.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(PRODUCT_PRICE_GREATER_THAN_ZERO_MESSAGE);
        //price and quantity
        //this logic needs to be further encapsulated
    }

    private ProductDTO saveAndReturnDTO(Product product) {
        product = productRepository.save(product);
        return productMapper.toDTO(product);
    }

}
