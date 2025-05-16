package com.dpilaloa.api.clients.movements.service.mapper;

import com.dpilaloa.api.clients.movements.domain.models.ClientEntity;
import com.dpilaloa.api.clients.movements.service.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client clientEntityToClient(ClientEntity clientEntity);

    ClientEntity clientToClientEntity(Client client);

}
