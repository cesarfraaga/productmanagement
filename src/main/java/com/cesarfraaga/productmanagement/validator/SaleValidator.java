package com.cesarfraaga.productmanagement.validator;

import com.cesarfraaga.productmanagement.dto.SaleDTO;
import com.cesarfraaga.productmanagement.entity.Client;
import com.cesarfraaga.productmanagement.repository.ClientRepository;
import com.cesarfraaga.productmanagement.util.validator.ValidationErrorMessages;
import com.cesarfraaga.productmanagement.util.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.cesarfraaga.productmanagement.util.ExceptionConstants.*;

@RequiredArgsConstructor
@Service
public class SaleValidator implements Validator<SaleDTO> {
    private final ClientRepository clientRepository;

    @Override
    public void doValidate(SaleDTO saleDTO, ValidationErrorMessages validationErrorMessages) {

        if (saleDTO.getClientDTO().getId() == null) {
            validationErrorMessages.addErrorMessage(CLIENT_ID_NULL_MESSAGE);
        }

        if (Objects.isNull(saleDTO.getShoppingCartDTO())) {
            validationErrorMessages.addErrorMessage(SALE_SHOPPINGCART_NOT_NULL_OR_EMPTY_MESSAGE);
        }

        Optional<Client> clientOptional = clientRepository.findById(saleDTO.getClientDTO().getId());
        if (clientOptional.isEmpty()) {
            validationErrorMessages.addErrorMessage(CLIENT_ID_NOT_FOUND_MESSAGE_PREFIX + saleDTO.getClientDTO().getId() + PERIOD);
        }

    }
}