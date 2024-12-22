package com.cesarfraaga.productmanagement.controller;

import com.cesarfraaga.productmanagement.dto.SaleDTO;
import com.cesarfraaga.productmanagement.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sale")
public class SaleController {

    @Autowired
    private SaleService service;

    @PostMapping(value = "/save")
    public ResponseEntity<SaleDTO> saveSale(@RequestBody SaleDTO saleDTO) {
        SaleDTO savedSale = service.createSale(saleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSale);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SaleDTO> findById(@PathVariable Long id) {
        SaleDTO saleDTO = service.findById(id);
        if (saleDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(saleDTO);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<SaleDTO>> findAll() {
        List<SaleDTO> saleDTOList = service.findAll();
        return ResponseEntity.ok(saleDTOList);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<SaleDTO> updateSale(@RequestBody SaleDTO saleDTO) {
        SaleDTO updatedSale = service.update(saleDTO);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
