package com.cesarfraaga.productmanagement.validator;

import com.cesarfraaga.productmanagement.dto.ProductDTO;
import com.cesarfraaga.productmanagement.dto.ShoppingCartDTO;
import com.cesarfraaga.productmanagement.entity.Product;
import com.cesarfraaga.productmanagement.repository.ProductRepository;
import com.cesarfraaga.productmanagement.util.validator.ValidationErrorMessages;
import com.cesarfraaga.productmanagement.util.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.cesarfraaga.productmanagement.util.ExceptionConstants.*;

@RequiredArgsConstructor
@Service
public class ShoppingCartValidator implements Validator<ShoppingCartDTO> {
    private final ProductRepository productRepository;

    @Override
    public void doValidate(ShoppingCartDTO shoppingCart, ValidationErrorMessages validationErrorMessages) {

        if (shoppingCart.getProductsIds() == null || shoppingCart.getProductsIds().isEmpty()) {
            validationErrorMessages.addErrorMessage(SHOPPINGCART_PRODUCT_ID_IS_NULL_OR_EMPTY);
        }

        for (ProductDTO productDTO : shoppingCart.getProductsDTO()) {
            Optional<Product> productOptional = productRepository.findById(productDTO.getId());

            if (productOptional.isEmpty()) {
                validationErrorMessages.addErrorMessage(PRODUCT_ID_NOT_FOUND_MESSAGE);
            } else {
                Product productEntity = productOptional.get();
                int requestedQuantity = productDTO.getQuantity();
                if (requestedQuantity > productEntity.getQuantity()) {
                    validationErrorMessages.addErrorMessage(SHOPPINGCART_STOCK_NOT_ENOUGH_PATTERN,
                            requestedQuantity, productEntity.getName(), productEntity.getId(), productEntity.getQuantity());
                }
            }
        }

    }
}
