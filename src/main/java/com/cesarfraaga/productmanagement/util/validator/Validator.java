package com.cesarfraaga.productmanagement.util.validator;

import java.util.List;

public interface Validator<T extends Validatable> {

    void doValidate(T validatable, ValidationErrorMessages validationErrorMessages);

    default List<ValidationError> validate(T validatable) {
        ValidationErrorMessages validationErrorMessages = new ValidationErrorMessages();
        doValidate(validatable, validationErrorMessages);
        return validationErrorMessages.getValidationErrors();
    }


}
