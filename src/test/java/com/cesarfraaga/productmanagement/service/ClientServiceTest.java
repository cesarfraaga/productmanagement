package com.cesarfraaga.productmanagement.service;

import com.cesarfraaga.productmanagement.dto.ClientDTO;
import com.cesarfraaga.productmanagement.entity.Client;
import com.cesarfraaga.productmanagement.exception.ResourceNotFoundException;
import com.cesarfraaga.productmanagement.repository.ClientRepository;
import com.cesarfraaga.productmanagement.util.ClientMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.cesarfraaga.productmanagement.util.ClientUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock //Simulação
    private ClientRepository clientRepository;

    @Spy
    private ClientMapper clientMapper;

    @Nested
    @DisplayName("Save tests")
    class MethodSaveTests {
        @Test
        void shouldSaveClientSuccessfully() {
            Client client = buildValidClient();
            ClientDTO clientDTO = buildValidClientDTO();

            when(clientRepository.save(any())).thenReturn(client);
            when(clientMapper.clientToEntity(any(ClientDTO.class))).thenReturn(client);
            when(clientMapper.clientToDTO(any(Client.class))).thenReturn(clientDTO);

            ClientDTO response = clientService.save(clientDTO);

            assertEquals(clientDTO.getId(), response.getId());  //garante que o valor <response.getId()> é igual ao valor esperado <clientDTO.getId()>
            assert response.getName().equals(clientDTO.getName());
            assert response.getCPF().equals(clientDTO.getCPF());
            assert response.getBirthDay().equals(clientDTO.getBirthDay());

        }

        @Test
        void shouldThrowExceptionWhenClientNameIsNull() {
            ClientDTO clientDTO = buildClientDTOWithNullName();

            assertThrows(ResourceNotFoundException.class, () -> clientService.save(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        //Posso fazer um teste parametrizado mudando apenas o valor da variável name
        @Test
        void shouldThrowExceptionWhenClientNameIsBlank() {
            ClientDTO clientDTO = buildClientDTOWithBlankName();

            assertThrows(ResourceNotFoundException.class, () -> clientService.save(clientDTO)); //assegura que a chamada do método <clientService.save()> com <esse parametro> <lanca uma ResourceNotFoundException>
            verify(clientMapper, never()).clientToDTO(any());   //verifica que o <mapper> <nunca> <chama esse método>
        }

        @Test
        void shouldThrowExceptionWhenClientNameLengthIsLessThanTwo() {
            ClientDTO clientDTO = buildClientDTOWithNameLengthIsLessThanTwo();

            assertThrows(IllegalArgumentException.class, () -> clientService.save(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientNameIsLongerThanFifty() {
            ClientDTO clientDTO = buildClientDTOWithNameLengthIsLongerThanFifty();

            assertThrows(IllegalArgumentException.class, () -> clientService.save(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientCpfIsNull() {
            ClientDTO clientDTO = buildClientDTOWithNullCpf();

            assertThrows(ResourceNotFoundException.class, () -> clientService.save(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientCpfIsEmpty() {
            ClientDTO clientDTO = buildClientDTOWithEmptyCpf();

            assertThrows(ResourceNotFoundException.class, () -> clientService.save(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientBirthDayIsNull() {
            ClientDTO clientDTO = buildClientDTOWithNullBirthDay();

            assertThrows(ResourceNotFoundException.class, () -> clientService.save(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientBirthDayIsEmpty() {
            ClientDTO clientDTO = buildClientDTOWithEmptyBirthDay();

            assertThrows(ResourceNotFoundException.class, () -> clientService.save(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }
    }

    @Nested
    @DisplayName("Update Tests")
    class MethodUpdateTests {
        @Test
        void shouldUpdateClientSuccessfully() { //Teste falhou, verificar o porque
            Client client = buildValidClient();
            ClientDTO clientDTO = buildValidClientDTO();

            //Mockei a verificação da existência do cliente
            when(clientRepository.existsById(clientDTO.getId())).thenReturn(true);

            when(clientRepository.save(any())).thenReturn(client);
            when(clientMapper.clientToEntity(any(ClientDTO.class))).thenReturn(client);
            when(clientMapper.clientToDTO(any(Client.class))).thenReturn(clientDTO);

            ClientDTO response = clientService.update(clientDTO);

            assertEquals(clientDTO.getId(), response.getId());
            assert response.getName().equals(clientDTO.getName());
            assert response.getCPF().equals(clientDTO.getCPF());
            assert response.getBirthDay().equals(clientDTO.getBirthDay());
        }

        @Test
        void shouldThrowExceptionWhenIdIsNull() {
            ClientDTO clientDTO = buildClientDTOWithNullName();

            assertThrows(ResourceNotFoundException.class, () -> clientService.update(clientDTO));
        }

        @Test
        void shouldThrowExceptionWhenIdDoesNotExist() {
            ClientDTO clientDTO = buildValidClientDTO();

            //Mockei o comportamento do repositório para indicar que o ID não existe
            when(clientRepository.existsById(clientDTO.getId())).thenReturn(false);

            assertThrows(ResourceNotFoundException.class, () -> clientService.update(clientDTO));
        }

        @Test
        void shouldThrowExceptionWhenClientNameIsNull() {
            ClientDTO clientDTO = buildClientDTOWithNullName();

            assertThrows(ResourceNotFoundException.class, () -> clientService.update(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientNameIsBlank() {
            ClientDTO clientDTO = buildClientDTOWithBlankName();

            assertThrows(ResourceNotFoundException.class, () -> clientService.update(clientDTO)); //assegura que a chamada do método <clientService.save()> com <esse parametro> <lanca uma ResourceNotFoundException>
            verify(clientMapper, never()).clientToDTO(any());   //verifica que o <mapper> <nunca> <chama esse método>
        }

        @Test
        void shouldThrowExceptionWhenClientNameLengthIsLessThanTwo() {
            ClientDTO clientDTO = buildClientDTOWithNameLengthIsLessThanTwo();

            when(clientRepository.existsById(clientDTO.getId())).thenReturn(true);

            assertThrows(IllegalArgumentException.class, () -> clientService.update(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientNameIsLongerThanFifty() {
            ClientDTO clientDTO = buildClientDTOWithNameLengthIsLongerThanFifty();

            when(clientRepository.existsById(clientDTO.getId())).thenReturn(true);

            assertThrows(IllegalArgumentException.class, () -> clientService.update(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientCpfIsNull() {
            ClientDTO clientDTO = buildClientDTOWithNullCpf();

            assertThrows(ResourceNotFoundException.class, () -> clientService.update(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientCpfIsEmpty() {
            ClientDTO clientDTO = buildClientDTOWithEmptyCpf();

            assertThrows(ResourceNotFoundException.class, () -> clientService.update(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientBirthDayIsNull() {
            ClientDTO clientDTO = buildClientDTOWithNullBirthDay();

            assertThrows(ResourceNotFoundException.class, () -> clientService.update(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientBirthDayIsEmpty() {
            ClientDTO clientDTO = buildClientDTOWithEmptyBirthDay();

            assertThrows(ResourceNotFoundException.class, () -> clientService.update(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }
    }

    @Nested
    @DisplayName("FindById Tests")
    class MethodFindByIdTests {
        @Test
        void shouldFindClientByIdSuccessfully() {
            Client client = buildValidClient();

            ClientDTO clientDTO = buildValidClientDTO();

            //Mockei o repositório para simular que o cliente existe
            when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

            //Mockei o mapper para converter a entidade no DTO
            when(clientMapper.clientToDTO(client)).thenReturn(clientDTO);

            ClientDTO response = clientService.findById(client.getId());

            assertNotNull(response);
            assertEquals(client.getId(), response.getId());
            assertEquals(client.getName(), response.getName());
            assertEquals(client.getCPF(), response.getCPF());
            assertEquals(client.getBirthDay(), response.getBirthDay());

            verify(clientRepository, times(1)).findById(client.getId());
        }

        @Test
        void shouldThrowExceptionWhenClientIdIsNull() {
            Long id = null;

            assertThrows(IllegalArgumentException.class, () -> clientService.findById(id));
        }

        @Test
        void shouldThrowExceptionWhenClientNotFound() {
            Client client = buildValidClient();

            //Mockei o repositório para retornar Optional.empty()
            when(clientRepository.findById(client.getId())).thenReturn(Optional.empty()); //Simula que o cliente não existe

            assertThrows(ResourceNotFoundException.class, () -> clientService.findById(client.getId()));

            //Pra verificar se o método do repositório foi chamado
            verify(clientRepository, times(1)).findById(client.getId());
        }
    }
    @Nested
    @DisplayName("DeleteById Tests")
    class MethodDeleteByIdTests {

    }

    @Nested
    @DisplayName("FindAll Tests")
    class MethodFindAllTests {

    }

}
