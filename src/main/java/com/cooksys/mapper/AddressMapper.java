package com.cooksys.mapper;

import org.mapstruct.Mapper;

import com.cooksys.dto.AddressDto;
import com.cooksys.dto.AddressDtoStripped;
import com.cooksys.entity.Address;
@Mapper(componentModel = "spring", uses = { ReferenceMapper.class })
public interface AddressMapper {
	AddressDtoStripped toStripped(Address entity);
	AddressDto toDto(Address entity);
	Address toEntity(AddressDto dto);
}