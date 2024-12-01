package com.cesarfraaga.productmanagement.util;

import com.cesarfraaga.productmanagement.dto.ClientDTO;
import com.cesarfraaga.productmanagement.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client clientToEntity(ClientDTO dto);

    ClientDTO clientToDTO(Client client);

}
