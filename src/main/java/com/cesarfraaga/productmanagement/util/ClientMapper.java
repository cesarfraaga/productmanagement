package com.cesarfraaga.productmanagement.util;

import com.cesarfraaga.productmanagement.dto.ClientDTO;
import com.cesarfraaga.productmanagement.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO clientToDTO(Client client);

    Client clientToEntity(ClientDTO dto);

}
