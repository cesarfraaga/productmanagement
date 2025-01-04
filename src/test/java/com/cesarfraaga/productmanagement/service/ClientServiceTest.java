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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock // Simulation
    private ClientRepository clientRepository;

    @Spy
    private ClientMapper clientMapper;

    @Nested
    @DisplayName("Save tests")
    class MethodSaveTests {
        @Test
        void shouldSaveClientSuccessfully() {
            Client client = new Client(1L, "César", "12345", "20/01/2003");
            ClientDTO clientDTO = new ClientDTO(1L, "César", "12345", "20/01/2003");

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
            ClientDTO clientDTO = new ClientDTO(1L, null, "12345", "20/01/2003");

            assertThrows(ResourceNotFoundException.class, () -> clientService.save(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        //Posso fazer um teste parametrizado mudando apenas o valor da variável name
        @Test
        void shouldThrowExceptionWhenClientNameIsBlank() {
            ClientDTO clientDTO = new ClientDTO(1L, "", "12345", "20/01/2003");

            assertThrows(ResourceNotFoundException.class, () -> clientService.save(clientDTO)); //assegura que a chamada do método <clientService.save()> com <esse parametro> <lanca uma ResourceNotFoundException>
            verify(clientMapper, never()).clientToDTO(any());   //verifica que o <mapper> <nunca> <chama esse método>
        }

        @Test
        void shouldThrowExceptionWhenClientNameLengthIsLessThanTwo() {
            ClientDTO clientDTO = new ClientDTO(1L, "A", "12345", "20/01/2003");

            assertThrows(IllegalArgumentException.class, () -> clientService.save(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }

        @Test
        void shouldThrowExceptionWhenClientNameIsLongerThanFifty() {
            ClientDTO clientDTO = new ClientDTO(1L, "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXY", "12345", "20/01/2003");

            assertThrows(IllegalArgumentException.class, () -> clientService.save(clientDTO));
            verify(clientMapper, never()).clientToDTO(any());
        }
    }

    @Nested
    @DisplayName("Update Tests")
    class MethodUpdateTests {

    }
}
