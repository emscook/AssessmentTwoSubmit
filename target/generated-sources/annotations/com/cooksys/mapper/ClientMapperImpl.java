package com.cooksys.mapper;

import com.cooksys.dto.ClientDto;
import com.cooksys.dto.ClientDtoStripped;
import com.cooksys.entity.Address;
import com.cooksys.entity.Client;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-06-08T15:25:52-0500",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_172 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Autowired
    private ReferenceMapper referenceMapper;

    @Override
    public ClientDtoStripped toStripped(Client entity) {
        if ( entity == null ) {
            return null;
        }

        ClientDtoStripped clientDtoStripped = new ClientDtoStripped();

        clientDtoStripped.setBirthday( entity.getBirthday() );
        clientDtoStripped.setName( entity.getName() );

        return clientDtoStripped;
    }

    @Override
    public ClientDto toDto(Client entity) {
        if ( entity == null ) {
            return null;
        }

        ClientDto clientDto = new ClientDto();

        clientDto.setPassword( entity.getPassword() );
        clientDto.setAddress( referenceMapper.toReference( entity.getAddress() ) );
        clientDto.setBirthday( entity.getBirthday() );
        clientDto.setName( entity.getName() );
        clientDto.setId( entity.getId() );

        return clientDto;
    }

    @Override
    public Client toEntity(ClientDto dto) {
        if ( dto == null ) {
            return null;
        }

        Client client = new Client();

        client.setAddress( referenceMapper.toEntity( dto.getAddress(), Address.class ) );
        client.setBirthday( dto.getBirthday() );
        client.setPassword( dto.getPassword() );
        client.setName( dto.getName() );
        client.setId( dto.getId() );

        return client;
    }
}
