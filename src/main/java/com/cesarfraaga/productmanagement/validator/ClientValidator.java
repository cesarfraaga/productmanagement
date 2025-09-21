package com.cesarfraaga.productmanagement.validator;

import com.cesarfraaga.productmanagement.dto.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator implements Validator<ClientDTO> {
    @Override
    public List<ValidationError> validate(ClientDTO validatable) {
        ArrayList<ValidationError> validationErrors = new ArrayList<>();
        return validationErrors;
    }
}
