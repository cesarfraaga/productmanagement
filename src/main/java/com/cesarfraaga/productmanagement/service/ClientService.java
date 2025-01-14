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

import static com.cesarfraaga.productmanagement.util.ExceptionConstants.*;


@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository repository;
    private final ClientMapper clientMapper;

    private final int MAX_LENGTH_CLIENT_NAME = 50;
    private final int MIN_LENGTH_CLIENT_NAME = 2;

    public ClientDTO save(ClientDTO clientDTO) {
        if (clientDTO.getName() == null || clientDTO.getName().isBlank())
            throw new ResourceNotFoundException(CLIENT_NAME_NULL_OR_EMPTY_MESSAGE);
        if (clientDTO.getName().length() < MIN_LENGTH_CLIENT_NAME || clientDTO.getName().length() > MAX_LENGTH_CLIENT_NAME)
            throw new IllegalArgumentException(CLIENT_NAME_LENGTH_MESSAGE);

        if (clientDTO.getCPF() == null || clientDTO.getCPF().isBlank())
            throw new ResourceNotFoundException(CLIENT_CPF_NULL_OR_EMPTY_MESSAGE);
        if (clientDTO.getBirthDay() == null || clientDTO.getBirthDay().isBlank())
            throw new ResourceNotFoundException(CLIENT_BIRTHDAY_NULL_OR_EMPTY_MESSAGE);

        Client client = clientMapper.clientToEntity(clientDTO);
        client = repository.save(client);
        return clientMapper.clientToDTO(client);
    }

    public ClientDTO update(ClientDTO clientDTO) {
        if (clientDTO.getId() == null || !repository.existsById(clientDTO.getId()))
            throw new ResourceNotFoundException(CLIENT_ID_NOT_FOUND_MESSAGE + clientDTO.getId() + PERIOD);
        if (clientDTO.getName() == null || clientDTO.getName().isBlank())
            throw new ResourceNotFoundException(CLIENT_NAME_NULL_OR_EMPTY_MESSAGE);
        if (clientDTO.getName().length() < MIN_LENGTH_CLIENT_NAME || clientDTO.getName().length() > MAX_LENGTH_CLIENT_NAME)
            throw new IllegalArgumentException(CLIENT_NAME_LENGTH_MESSAGE);

        if (clientDTO.getCPF() == null || clientDTO.getCPF().isBlank())
            throw new ResourceNotFoundException(CLIENT_CPF_NULL_OR_EMPTY_MESSAGE);
        if (clientDTO.getBirthDay() == null || clientDTO.getBirthDay().isBlank())
            throw new ResourceNotFoundException(CLIENT_BIRTHDAY_NULL_OR_EMPTY_MESSAGE);

        Client client = clientMapper.clientToEntity(clientDTO);
        client = repository.save(client);
        return clientMapper.clientToDTO(client);
    }

    public ClientDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException(CLIENT_ID_NULL_MESSAGE);
        }

        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CLIENT_ID_NOT_FOUND_MESSAGE + id + PERIOD));
        return clientMapper.clientToDTO(client);
    }

    public void deleteById(Long id) {
        if (id == null || !repository.existsById(id))
            throw new ResourceNotFoundException(CLIENT_NOT_FOUND_MESSAGE);
        repository.deleteById(id);
    }

    public List<ClientDTO> findAll() {
        List<Client> clientList = repository.findAll();
        List<ClientDTO> clientDTOList = new ArrayList<>();

        if (clientList.isEmpty())
            throw new ResourceNotFoundException(CLIENTS_NOT_FOUND_MESSAGE);

        for (Client client : clientList) {
            ClientDTO clientDTO = clientMapper.clientToDTO(client);
            clientDTOList.add(clientDTO);
        }
        return clientDTOList;
    }
    //Criar método validateBeforeSaveOrUpdate para reutilização de código
}
