package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.ClientDTO;
import com.cesarfraaga.productmanagement.entity.Client;
import com.cesarfraaga.productmanagement.exception.ResourceNotFoundException;
import com.cesarfraaga.productmanagement.repository.ClientRepository;
import com.cesarfraaga.productmanagement.util.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper clientMapper;

    public ClientDTO save(ClientDTO clientDTO) {
        if (clientDTO.getName() == null || clientDTO.getName().isBlank())
            throw new ResourceNotFoundException("Name cannot be null or empty.");
        if (clientDTO.getName().length() > 50)
            throw new IllegalArgumentException("Name cannot be longer than 50 characters");
        if (clientDTO.getName().length() < 2)
            throw new IllegalArgumentException("Name cannot be shorter than 2 characters");

        if (clientDTO.getCPF() == null || clientDTO.getCPF().isBlank())
            throw new ResourceNotFoundException("CPF cannot be null or empty.");
        if (clientDTO.getBirthDay() == null || clientDTO.getBirthDay().isBlank())
            throw new ResourceNotFoundException("Birth day cannot be null or empty.");

        Client client = clientMapper.clientToEntity(clientDTO);
        client = repository.save(client);
        return clientMapper.clientToDTO(client);
    }

    public ClientDTO update(ClientDTO clientDTO) {
        if (clientDTO.getId() == null || !repository.existsById(clientDTO.getId()))
            throw new ResourceNotFoundException("Client not found with ID " + clientDTO.getId() + ".");
        if (clientDTO.getName() == null || clientDTO.getName().isBlank())
            throw new ResourceNotFoundException("Name cannot be null or empty.");
        if (clientDTO.getName().length() > 50)
            throw new IllegalArgumentException("Name cannot be longer than 50 characters");
        if (clientDTO.getName().length() < 2)
            throw new IllegalArgumentException("Name cannot be shorter than 2 characters");

        if (clientDTO.getCPF() == null || clientDTO.getCPF().isBlank())
            throw new ResourceNotFoundException("CPF cannot be null or empty.");
        if (clientDTO.getBirthDay() == null || clientDTO.getBirthDay().isBlank())
            throw new ResourceNotFoundException("Birth day cannot be null or empty.");

        Client client = clientMapper.clientToEntity(clientDTO);
        client = repository.save(client);
        return clientMapper.clientToDTO(client);
    }

    public ClientDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }

        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id + "."));
        return clientMapper.clientToDTO(client);
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Client not found.");
        repository.deleteById(id);
    }

    public List<ClientDTO> findAll() {
        List<Client> clientList = repository.findAll();
        List<ClientDTO> clientDTOList = new ArrayList<>();

        if (clientList.isEmpty())
            throw new ResourceNotFoundException("No clients available.");

        for (Client client : clientList) {
            ClientDTO clientDTO = clientMapper.clientToDTO(client);
            clientDTOList.add(clientDTO);
        }
        return clientDTOList;
    }

}
