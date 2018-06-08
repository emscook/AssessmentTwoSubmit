package com.cooksys.mapper;

import org.mapstruct.Mapper;

import com.cooksys.dto.ClientDto;
import com.cooksys.dto.ClientDtoStripped;
import com.cooksys.entity.Client;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class })
public interface ClientMapper {
	ClientDtoStripped toStripped(Client entity);
	ClientDto toDto(Client entity);
	
	Client toEntity(ClientDto dto);
}
