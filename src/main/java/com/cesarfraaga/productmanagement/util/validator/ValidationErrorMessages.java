package com.cesarfraaga.productmanagement.util.validator;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class ValidationErrorMessages {

    private ArrayList<ValidationError> validationErrors = new ArrayList<>();

    public void addErrorMessage(String message) {
        validationErrors.add(new ValidationError(message));
    }

    public void addErrorMessage(String pattern, Object... args) {
        addErrorMessage(String.format(pattern, args));
    }

    //public void addErrorMessage2(String pattern, int requestedQuantity, String name, Long id, Integer quantity) {
    //    addErrorMessage(String.format(pattern, requestedQuantity, name, id, quantity));
    //}
}
