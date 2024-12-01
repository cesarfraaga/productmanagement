package com.cesarfraaga.productmanagement.controller;

import com.cesarfraaga.productmanagement.dto.SellerDTO;
import com.cesarfraaga.productmanagement.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/seller")
public class SellerController {

    @Autowired
    private SellerService service;

    @PostMapping(value = "/save")
    public ResponseEntity<SellerDTO> saveSale(@RequestBody SellerDTO sellerDTO) {
        SellerDTO savedSeller = service.save(sellerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeller);
    }

    @GetMapping(value = "{/id}")
    public ResponseEntity<SellerDTO> findById(@PathVariable Long id) {
        SellerDTO sellerDTO = service.findById(id);
        if (sellerDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sellerDTO);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<SellerDTO>> findAll() {
        List<SellerDTO> sellerDTOList = service.findAll();
        return ResponseEntity.ok(sellerDTOList);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<SellerDTO> updateSale(@RequestBody SellerDTO sellerDTO) {
        SellerDTO updatedSeller = service.update(sellerDTO);
        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}