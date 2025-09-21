package com.cesarfraaga.productmanagement.util.validator;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationHelper {

    public static void throwIllegalArgumentExceptionInCaseOfErrors(List<ValidationError> validationErrors, String pattern) {
        if (!validationErrors.isEmpty()) {
            String messages = validationErrors.stream().map(ValidationError::getMessage).collect(Collectors.joining("\n"));
            throw new IllegalArgumentException(String.format(pattern, messages));
        }
    }
}
