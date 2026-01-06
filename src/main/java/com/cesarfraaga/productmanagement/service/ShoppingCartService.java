package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.ProductDTO;
import com.cesarfraaga.productmanagement.dto.ShoppingCartDTO;
import com.cesarfraaga.productmanagement.entity.Product;
import com.cesarfraaga.productmanagement.entity.ShoppingCart;
import com.cesarfraaga.productmanagement.exception.ResourceNotFoundException;
import com.cesarfraaga.productmanagement.repository.ProductRepository;
import com.cesarfraaga.productmanagement.repository.ShoppingCartRepository;
import com.cesarfraaga.productmanagement.util.ShoppingCartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.cesarfraaga.productmanagement.util.ExceptionConstants.*;

@RequiredArgsConstructor
@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final ProductRepository productRepository;

    public ShoppingCartDTO createShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        validateBeforeSaveOrUpdate(shoppingCartDTO);

        return saveShoppingCart(shoppingCartDTO);
    }

    private ShoppingCartDTO saveShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = shoppingCartMapper.toEntityShoppingCart(shoppingCartDTO);

        List<Long> productsIds = shoppingCartDTO.getProductsIds();

        List<Product> products = productRepository.findAllById(productsIds); //Carrega os produtos do banco usando os IDs
        shoppingCart.setProducts(products); //Associando os produtos ao carrinho (sem sobrescrever a relação)

        shoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDTOShoppingCart(shoppingCart);
    }

    public ShoppingCartDTO update(ShoppingCartDTO shoppingCartDTO) {
        if (shoppingCartDTO.getId() == null || !shoppingCartRepository.existsById(shoppingCartDTO.getId()))
            throw new ResourceNotFoundException(SHOPPINGCART_ID_NOT_FOUND_MESSAGE + shoppingCartDTO.getId() + PERIOD);

        validateBeforeSaveOrUpdate(shoppingCartDTO);

        ShoppingCart shoppingCart = shoppingCartMapper.toEntityShoppingCart(shoppingCartDTO);
        shoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDTOShoppingCart(shoppingCart);
    }

    public ShoppingCartDTO findById(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByIdWithProducts(id)
                .orElseThrow(() -> new ResourceNotFoundException(SHOPPINGCART_NOT_FOUND_MESSAGE));

        return shoppingCartMapper.toDTOShoppingCart(shoppingCart);
    }

    public void deleteById(Long id) {
        if (!shoppingCartRepository.existsById(id))
            throw new ResourceNotFoundException(SHOPPINGCART_ID_NOT_FOUND_MESSAGE + id + PERIOD);
        shoppingCartRepository.deleteById(id);
    }

    public List<ShoppingCartDTO> findAll() {
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();
        List<ShoppingCartDTO> shoppingCartDTOList = new ArrayList<>();

        if (shoppingCartList.isEmpty())
            throw new ResourceNotFoundException(SHOPPINGCART_NO_AVAILABLE_MESSAGE);

        for (ShoppingCart shoppingCart : shoppingCartList) {
            ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toDTOShoppingCart(shoppingCart);
            shoppingCartDTOList.add(shoppingCartDTO);
        }
        return shoppingCartDTOList;
    }

    private void validateBeforeSaveOrUpdate(ShoppingCartDTO shoppingCartDTO) {

    }

    private void validateBeforeSaveOrUpdateAndUpdateProductQuantity(ShoppingCartDTO shoppingCartDTO) {
        if (shoppingCartDTO.getProductsIds() == null || shoppingCartDTO.getProductsIds().isEmpty())
            throw new IllegalArgumentException(SHOPPINGCART_PRODUCT_ID_IS_NULL_OR_EMPTY);

        for (ProductDTO productDTO : shoppingCartDTO.getProductsDTO()) {
            Product product = productRepository.findById(productDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productDTO.getId()));

            int requestedQuantity = productDTO.getQuantity();
            if (product.getQuantity() < requestedQuantity) {
                throw new IllegalArgumentException("Not enough stock for product id: " + product.getId());
            }

            product.setQuantity(product.getQuantity() - requestedQuantity);
            productRepository.save(product);
        }
    }

}

