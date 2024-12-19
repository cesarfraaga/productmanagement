package com.cesarfraaga.productmanagement.controller;

import com.cesarfraaga.productmanagement.dto.ShoppingCartDTO;
import com.cesarfraaga.productmanagement.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService service;

    @PostMapping(value = "/save")
    public ResponseEntity<ShoppingCartDTO> save(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCartDTO savedShoppingCartDTO = service.save(shoppingCartDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShoppingCartDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ShoppingCartDTO> findById(@PathVariable Long id) {
        ShoppingCartDTO shoppingCartDTO = service.findById(id);
        if (shoppingCartDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(shoppingCartDTO);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<ShoppingCartDTO>> findAll() {
        List<ShoppingCartDTO> shoppingCartDTOList = service.findAll();
        return ResponseEntity.ok(shoppingCartDTOList);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ShoppingCartDTO> updateClient(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCartDTO updatedShoppingCart = service.update(shoppingCartDTO);
        return ResponseEntity.ok(updatedShoppingCart);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
