package com.cesarfraaga.productmanagement.validator;

import com.cesarfraaga.productmanagement.dto.ProductDTO;
import com.cesarfraaga.productmanagement.dto.ShoppingCartDTO;
import com.cesarfraaga.productmanagement.entity.Product;
import com.cesarfraaga.productmanagement.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.cesarfraaga.productmanagement.util.ExceptionConstants.PRODUCT_ID_NOT_FOUND_MESSAGE;
import static com.cesarfraaga.productmanagement.util.ExceptionConstants.SHOPPINGCART_PRODUCT_ID_IS_NULL_OR_EMPTY;

@RequiredArgsConstructor
@Service
public class ShoppingCartValidator implements Validator<ShoppingCartDTO> {
    private final ProductRepository productRepository;

    @Override
    public List<ValidationError> validate(ShoppingCartDTO shoppingCart) {

        ArrayList<ValidationError> validationErrors = new ArrayList<>();

        if (shoppingCart.getProductsIds() == null || shoppingCart.getProductsIds().isEmpty()) {
            validationErrors.add(new ValidationError(SHOPPINGCART_PRODUCT_ID_IS_NULL_OR_EMPTY));
        }

        for (ProductDTO productDTO : shoppingCart.getProductsDTO()) {
            Optional<Product> productOptional = productRepository.findById(productDTO.getId());

            if (productOptional.isEmpty()) {
                validationErrors.add(new ValidationError(PRODUCT_ID_NOT_FOUND_MESSAGE));
            } else {
                Product productEntity = productOptional.get();
                int requestedQuantity = productDTO.getQuantity();
                if (requestedQuantity > productEntity.getQuantity()) {
                    String pattern = "Shopping cart asked for %s units of product \"%s\" (id=%s). Maximum available: %s.";
                    String stockNotEnoughMessage = String.format(pattern, requestedQuantity, productEntity.getName(), productEntity.getId(), productEntity.getQuantity());
                    validationErrors.add(new ValidationError(stockNotEnoughMessage));
                }
            }
        }

        return validationErrors;
    }

}
