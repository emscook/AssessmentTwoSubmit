package com.cooksys.mapper;

import com.cooksys.dto.AddressDto;
import com.cooksys.dto.AddressDtoStripped;
import com.cooksys.dto.datatype.Reference;
import com.cooksys.entity.Address;
import com.cooksys.entity.Client;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-06-08T15:25:52-0500",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_172 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Autowired
    private ReferenceMapper referenceMapper;

    @Override
    public AddressDtoStripped toStripped(Address entity) {
        if ( entity == null ) {
            return null;
        }

        AddressDtoStripped addressDtoStripped = new AddressDtoStripped();

        addressDtoStripped.setStreet( entity.getStreet() );
        addressDtoStripped.setCity( entity.getCity() );
        addressDtoStripped.setState( entity.getState() );

        return addressDtoStripped;
    }

    @Override
    public AddressDto toDto(Address entity) {
        if ( entity == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        Set<Reference<Client, Long>> set = clientSetToReferenceSet( entity.getResidents() );
        if ( set != null ) {
            addressDto.setResidents( set );
        }
        addressDto.setStreet( entity.getStreet() );
        addressDto.setCity( entity.getCity() );
        addressDto.setState( entity.getState() );
        addressDto.setId( entity.getId() );

        return addressDto;
    }

    @Override
    public Address toEntity(AddressDto dto) {
        if ( dto == null ) {
            return null;
        }

        Address address = new Address();

        Set<Client> set = referenceSetToClientSet( dto.getResidents() );
        if ( set != null ) {
            address.setResidents( set );
        }
        address.setStreet( dto.getStreet() );
        address.setCity( dto.getCity() );
        address.setState( dto.getState() );
        address.setId( dto.getId() );

        return address;
    }

    protected Set<Reference<Client, Long>> clientSetToReferenceSet(Set<Client> set) {
        if ( set == null ) {
            return null;
        }

        Set<Reference<Client, Long>> set_ = new HashSet<Reference<Client, Long>>();
        for ( Client client : set ) {
            set_.add( referenceMapper.toReference( client ) );
        }

        return set_;
    }

    protected Set<Client> referenceSetToClientSet(Set<Reference<Client, Long>> set) {
        if ( set == null ) {
            return null;
        }

        Set<Client> set_ = new HashSet<Client>();
        for ( Reference<Client, Long> reference : set ) {
            set_.add( referenceMapper.toEntity( reference, Client.class ) );
        }

        return set_;
    }
}
