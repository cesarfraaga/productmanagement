package com.cesarfraaga.productmanagement.validator;

import com.cesarfraaga.productmanagement.dto.SaleDTO;
import com.cesarfraaga.productmanagement.entity.Client;
import com.cesarfraaga.productmanagement.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.cesarfraaga.productmanagement.util.ExceptionConstants.*;

@RequiredArgsConstructor
@Service
public class SaleValidator implements Validator<SaleDTO> {
    private final ClientRepository clientRepository;

    @Override
    public List<ValidationError> validate(SaleDTO saleDTO) {

        ArrayList<ValidationError> validationErrors = new ArrayList<>();

        if (saleDTO.getClientDTO().getId() == null) {
            validationErrors.add(new ValidationError(CLIENT_ID_NULL_MESSAGE));
        }

        if (Objects.isNull(saleDTO.getShoppingCartDTO())) {
            validationErrors.add(new ValidationError(SALE_SHOPPINGCART_NOT_NULL_OR_EMPTY_MESSAGE));
        }

        Optional<Client> clientOptional = clientRepository.findById(saleDTO.getClientDTO().getId());
        if (clientOptional.isEmpty()) {
            validationErrors.add(new ValidationError(CLIENT_ID_NOT_FOUND_MESSAGE + saleDTO.getClientDTO().getId() + PERIOD));
        }

        return validationErrors;

    }
}