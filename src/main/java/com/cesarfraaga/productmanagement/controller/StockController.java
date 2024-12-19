package com.cesarfraaga.productmanagement.controller;

import com.cesarfraaga.productmanagement.dto.ShoppingCartDTO;
import com.cesarfraaga.productmanagement.dto.StockDTO;
import com.cesarfraaga.productmanagement.service.ShoppingCartService;
import com.cesarfraaga.productmanagement.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "stock")
public class StockController {

    @Autowired
    private StockService service;

    @PostMapping(value = "/save")
    public ResponseEntity<StockDTO> save(@RequestBody StockDTO stockDTO) {
        StockDTO savedStockDTO = service.save(stockDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStockDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StockDTO> findById(@PathVariable Long id) {
        StockDTO stockDTO = service.findById(id);
        if (stockDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(stockDTO);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<StockDTO>> findAll() {
        List<StockDTO> stockDTOList = service.findAll();
        return ResponseEntity.ok(stockDTOList);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<StockDTO> update(@RequestBody StockDTO stockDTO) {
        StockDTO updatedStockDTO = service.update(stockDTO);
        return ResponseEntity.ok(updatedStockDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}