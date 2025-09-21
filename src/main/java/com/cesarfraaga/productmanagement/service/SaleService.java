package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.ClientDTO;
import com.cesarfraaga.productmanagement.dto.SaleDTO;
import com.cesarfraaga.productmanagement.dto.ShoppingCartDTO;
import com.cesarfraaga.productmanagement.entity.Sale;
import com.cesarfraaga.productmanagement.exception.ResourceNotFoundException;
import com.cesarfraaga.productmanagement.repository.ClientRepository;
import com.cesarfraaga.productmanagement.repository.SaleRepository;
import com.cesarfraaga.productmanagement.util.ClientMapper;
import com.cesarfraaga.productmanagement.util.SaleMapper;
import com.cesarfraaga.productmanagement.validator.SaleValidator;
import com.cesarfraaga.productmanagement.util.validator.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.cesarfraaga.productmanagement.util.ExceptionConstants.*;

@RequiredArgsConstructor
@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    private final ShoppingCartService shoppingCartService;

    private final SaleValidator validator;

    //this logic needs to be further encapsulated
    public SaleDTO createSale(SaleDTO saleDTO) {
        List<ValidationError> validationErrors = validator.validate(saleDTO);

        if (!validationErrors.isEmpty()) {
            String messages = validationErrors.stream().map(ValidationError::getMessage).collect(Collectors.joining("\n"));
            String pattern = "Could not validate sale due to the following errors: \n%s";
            throw new IllegalArgumentException(String.format(pattern, messages));
        }

        ClientDTO clientDTO = clientMapper.clientToDTO(clientRepository.findById(saleDTO.getClientDTO().getId()).get());
        saleDTO.setClientDTO(clientDTO);

        ShoppingCartDTO shoppingCartDTO = shoppingCartService.createShoppingCart(saleDTO.getShoppingCartDTO());
        saleDTO.setShoppingCartDTO(shoppingCartDTO);

        Sale savedSale = saleRepository.save(saleMapper.toEntitySale(saleDTO));

        return saleMapper.toDTOSale(savedSale);
    }

    public SaleDTO update(SaleDTO saleDTO) {
        if (saleDTO.getId() == null || !saleRepository.existsById(saleDTO.getId()))
            throw new ResourceNotFoundException(SALE_ID_NOT_FOUND_MESSAGE + saleDTO.getId() + PERIOD);
        if (saleDTO.getClientDTO().getId() == null)
            throw new IllegalArgumentException(SALE_CLIENT_ID_NOT_NULL_OR_EMPTY_MESSAGE);
        if (saleDTO.getShoppingCartDTO().getId() == null)
            throw new IllegalArgumentException(SALE_SHOPPINGCART_NOT_NULL_OR_EMPTY_MESSAGE);

        Sale sale = saleMapper.toEntitySale(saleDTO);
        sale = saleRepository.save(sale);
        return saleMapper.toDTOSale(sale);
    }

    public SaleDTO findById(Long id) {
        Sale sale = saleRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(SALE_ID_NOT_FOUND_MESSAGE + id + PERIOD));
        return saleMapper.toDTOSale(sale);
    }

    public void deleteById(Long id) {
        if (!saleRepository.existsById(id))
            throw new ResourceNotFoundException(SALE_NOT_FOUND_FOR_DELETE_MESSAGE);
        saleRepository.deleteById(id);
    }

    public List<SaleDTO> findAll() {
        List<Sale> saleList = saleRepository.findAll();
        List<SaleDTO> saleDTOList = new ArrayList<>();

        if (saleList.isEmpty())
            throw new ResourceNotFoundException(SALE_NOT_FOUND_MESSAGE);

        for (Sale sale : saleList) {
            SaleDTO saleDTO = saleMapper.toDTOSale(sale);
            saleDTOList.add(saleDTO);
        }
        return saleDTOList;

    }
}