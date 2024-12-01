package com.cesarfraaga.productmanagement.controller;

import com.cesarfraaga.productmanagement.dto.ClientDTO;
import com.cesarfraaga.productmanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "client")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping(value = "/save")
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO savedClient = service.save(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @GetMapping(value = "{/id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO clientDTO = service.findById(id);
        if (clientDTO == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<ClientDTO> clientDTOList = service.findAll();
        return ResponseEntity.ok(clientDTOList);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO updatedClient = service.update(clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
