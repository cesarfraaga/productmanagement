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

    public ClientDTO saveClient(final ClientDTO clientDTO) {
        validateBeforeSaveOrUpdate(clientDTO);

        Client client = clientMapper.clientToEntity(clientDTO);
        return saveAndReturnDTO(client);
    }

    public ClientDTO updateClient(final ClientDTO clientDTO) {
        if (clientDTO.getId() == null || !repository.existsById(clientDTO.getId()))
            throw new ResourceNotFoundException(CLIENT_ID_NOT_FOUND_MESSAGE + clientDTO.getId() + PERIOD);

        validateBeforeSaveOrUpdate(clientDTO);

        Client existingClient = repository.findById(clientDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException(CLIENT_ID_NOT_FOUND_MESSAGE + clientDTO.getId() + PERIOD));
        //At some point, i'll need to change the logic. User won't be able to change their cpf, only an administrator can
        existingClient.setName(clientDTO.getName());
        existingClient.setCpf(clientDTO.getCpf());
        existingClient.setBirthDay(clientDTO.getBirthDay());
        //Solução inicial para o update de usuário (sem mexer nas sales)

        return saveAndReturnDTO(existingClient);

        /*Client client = clientMapper.clientToEntity(clientDTO);
        return saveAndReturnDTO(client);*/
    }

    public ClientDTO findById(final Long id) {
        if (id == null) {
            throw new IllegalArgumentException(CLIENT_ID_NULL_MESSAGE);
        }

        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CLIENT_ID_NOT_FOUND_MESSAGE + id + PERIOD));
        return clientMapper.clientToDTO(client);
    }

    public void deleteById(final Long id) {
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

    private void validateBeforeSaveOrUpdate(final ClientDTO clientDTO) {

        validateName(clientDTO.getName());

        validateCpf(clientDTO.getCpf());

        validateBirthDay(clientDTO.getBirthDay());
    }

    private ClientDTO saveAndReturnDTO(Client client) {
        client = repository.save(client);
        return clientMapper.clientToDTO(client);
    }

    private static void validateName(final String name) {

        int maxLengthClientName = 50;
        int minLengthClientName = 2;

        if (name == null || name.isBlank())
            throw new ResourceNotFoundException(CLIENT_NAME_NULL_OR_EMPTY_MESSAGE);
        if (name.length() < minLengthClientName || name.length() > maxLengthClientName)
            throw new IllegalArgumentException(CLIENT_NAME_LENGTH_MESSAGE);
        if (!name.matches(BASIC_CHARACTER))
            throw new IllegalArgumentException(CLIENT_NAME_NOT_SPECIAL_CHARACTER_MESSAGE);
    }

    private static void validateCpf(final String cpf) {

        if (cpf == null || cpf.isBlank())
            throw new ResourceNotFoundException(CLIENT_CPF_NULL_OR_EMPTY_MESSAGE);
        if (!cpf.matches(ONLY_NUMBER))
            throw new IllegalArgumentException(CLIENT_CPF_ONLY_NUMBER_MESSAGE);
    }

    private static void validateBirthDay(final String birthDay) {

        if (birthDay == null || birthDay.isBlank())
            throw new ResourceNotFoundException(CLIENT_BIRTHDAY_NULL_OR_EMPTY_MESSAGE);
        if (!birthDay.matches(ONLY_NUMBER))
            throw new IllegalArgumentException(CLIENT_BIRTHDAY_ONLY_NUMBER_MESSAGE);
        //Temos que arrumar o formato do birthday para sempre converter para: MM/dd/yyy
    }
}
