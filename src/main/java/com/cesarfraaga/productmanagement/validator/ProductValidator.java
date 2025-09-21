package com.cesarfraaga.productmanagement.validator;

import com.cesarfraaga.productmanagement.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductValidator implements Validator<ProductDTO> {
    @Override
    public List<ValidationError> validate(ProductDTO validatable) {
        ArrayList<ValidationError> validationErrors = new ArrayList<>();
        return validationErrors;
    }
}
