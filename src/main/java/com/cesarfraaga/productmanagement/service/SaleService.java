package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.SaleDTO;
import com.cesarfraaga.productmanagement.entity.Sale;
import com.cesarfraaga.productmanagement.exception.ResourceNotFoundException;
import com.cesarfraaga.productmanagement.repository.SaleRepository;
import com.cesarfraaga.productmanagement.util.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    public SaleDTO save(SaleDTO saleDTO) {
        if (saleDTO.getClientId() == null)
            throw new IllegalArgumentException("Client ID cannot be null or empty.");
        if (saleDTO.getShoppingCartId() == null)
            throw new IllegalArgumentException("Shopping Cart ID cannot be null or empty.");

        Sale sale = saleMapper.toEntitySale(saleDTO);
        sale = saleRepository.save(sale);
        return saleMapper.toDTOSale(sale);
    }

    public SaleDTO update(SaleDTO saleDTO) {
        if (saleDTO.getId() == null || !saleRepository.existsById(saleDTO.getId()))
            throw new ResourceNotFoundException("Sale not found with ID " + saleDTO.getId() + ".");
        if (saleDTO.getClientId() == null)
            throw new IllegalArgumentException("Client ID cannot be null or empty.");
        if (saleDTO.getShoppingCartId() == null)
            throw new IllegalArgumentException("Shopping Cart ID cannot be null or empty.");

        Sale sale = saleMapper.toEntitySale(saleDTO);
        sale = saleRepository.save(sale);
        return saleMapper.toDTOSale(sale);
    }

    public SaleDTO findById(Long id) {
        Sale sale = saleRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Sale not found with id + " + id + "."));
        return saleMapper.toDTOSale(sale);
    }

    public void deleteById(Long id) {
        if (!saleRepository.existsById(id))
            throw new ResourceNotFoundException("Sale not found for delete");
        saleRepository.deleteById(id);
    }

    public List<SaleDTO> findAll() {
        List<Sale> saleList = saleRepository.findAll();
        List<SaleDTO> saleDTOList = new ArrayList<>();

        if (saleList.isEmpty())
            throw new ResourceNotFoundException("Sale not found.");

        for (Sale sale : saleList) {
            SaleDTO saleDTO = saleMapper.toDTOSale(sale);
            saleDTOList.add(saleDTO);
        }
        return saleDTOList;

    }
}
