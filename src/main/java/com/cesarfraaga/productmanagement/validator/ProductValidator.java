package com.cesarfraaga.productmanagement.validator;

import com.cesarfraaga.productmanagement.dto.ProductDTO;
import com.cesarfraaga.productmanagement.util.validator.ValidationErrorMessages;
import com.cesarfraaga.productmanagement.util.validator.Validator;

public class ProductValidator implements Validator<ProductDTO> {
    @Override
    public void doValidate(ProductDTO validatable, ValidationErrorMessages validationErrorMessages) {
    }
}
