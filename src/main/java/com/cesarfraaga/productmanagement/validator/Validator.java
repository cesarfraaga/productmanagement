package com.cesarfraaga.productmanagement.validator;

import java.util.List;

public interface Validator<T extends Validatable> {
    List<ValidationError> validate(T validatable);
}
