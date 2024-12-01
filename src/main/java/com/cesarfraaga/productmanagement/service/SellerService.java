package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.SellerDTO;
import com.cesarfraaga.productmanagement.entity.Seller;
import com.cesarfraaga.productmanagement.exception.ResourceNotFoundException;
import com.cesarfraaga.productmanagement.repository.SellerRepository;
import com.cesarfraaga.productmanagement.util.SellerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;

    public SellerDTO save(SellerDTO sellerDTO) {
        Seller seller = sellerMapper.toEntitySeller(sellerDTO);
        seller = sellerRepository.save(seller);
        return sellerMapper.toDTOSeller(seller);
    }

    public SellerDTO update(SellerDTO sellerDTO) {
        Seller seller = sellerMapper.toEntitySeller(sellerDTO);
        seller = sellerRepository.save(seller);
        return sellerMapper.toDTOSeller(seller);
    }

    public SellerDTO findById(Long id) {
        Seller seller = sellerRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Seller not found with id + " + id + "."));
        return sellerMapper.toDTOSeller(seller);
    }

    public void deleteById(Long id) {
        if (!sellerRepository.existsById(id))
            throw new ResourceNotFoundException("Seller not found for delete");
        sellerRepository.deleteById(id);
    }

    public List<SellerDTO> findAll() {
        List<Seller> sellerList = sellerRepository.findAll();
        List<SellerDTO> sellerDTOList = new ArrayList<>();

        if (sellerList.isEmpty())
            throw new ResourceNotFoundException("Seller not found.");

        for (Seller seller : sellerList) {
            SellerDTO sellerDTO = sellerMapper.toDTOSeller(seller);
            sellerDTOList.add(sellerDTO);
        }
        return sellerDTOList;

    }
}
