package org.numo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.numo.dao.entity.Client;
import org.numo.dto.client.ClientDto;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "account", target = "accountDto")
    @Mapping(source = "company", target = "companyDto")
    ClientDto toDto(Client client);

    @Mapping(source = "accountDto", target = "account")
    @Mapping(source = "companyDto", target = "company")
    Client toEntity(ClientDto clientDto);
}