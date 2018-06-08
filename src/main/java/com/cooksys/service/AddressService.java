package com.cooksys.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cooksys.dto.ClientDto;
import com.cooksys.dto.ClientDtoStripped;
import com.cooksys.dto.AddressDto;
import com.cooksys.dto.AddressDtoStripped;
import com.cooksys.entity.Client;
import com.cooksys.entity.Address;
import com.cooksys.exception.ReferencedEntityNotFoundException;
import com.cooksys.mapper.AddressMapper;
import com.cooksys.mapper.ClientMapper;
import com.cooksys.repository.AddressRepository;
import com.cooksys.mapper.AddressMapper;
import com.cooksys.repository.AddressRepository;
import com.cooksys.mapper.ClientMapper;
import com.cooksys.repository.ClientRepository;

@Service
public class AddressService {

	private AddressRepository repo;
	private AddressMapper mapper;
	private ClientRepository crepo;
	private ClientMapper cmapper;
	
	public AddressService(AddressRepository repo, AddressMapper mapper, ClientRepository crepo, ClientMapper cmapper) {
		this.repo = repo;
		this.mapper = mapper;
		this.crepo = crepo;
		this.cmapper = cmapper;
	}
	
	public AddressDtoStripped get(Long id) {
		return mapper.toStripped(repo.getOne(id));
	}
	
//	public List<AddressDto> getAll(Long id) {
//		return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
//	}
	
	public List<AddressDtoStripped> getAddress(String city, String state) {
		if(city == null && state == null) {
			return repo.findAll().stream().map(mapper::toStripped).collect(Collectors.toList());
		}
		else if(city != null && state != null) {
			return repo.findByCityAndStateEquals(city,state).stream().map(mapper::toStripped).collect(Collectors.toList());
		}
		else if(city != null){
			return repo.findByCity(city).stream().map(mapper::toStripped).collect(Collectors.toList());
		}
		else {
			return repo.findByState(state).stream().map(mapper::toStripped).collect(Collectors.toList());
		}
	}
	
	public List<ClientDtoStripped> getResidents(Long id) {
//		return crepo.findByAddressEquals(repo.getOne(id)).stream().map(cmapper::toDto).collect(Collectors.toList());
		return repo.getOne(id).getResidents().stream().map(cmapper::toStripped).collect(Collectors.toList());
		
	}
	
	public Long post(AddressDto addressDto) {
		addressDto.setId(null);
		Address myDude = mapper.toEntity(addressDto);
		Set<Client> thePrev = myDude.getResidents();
		for(Client hesMoving : thePrev) {
			Address prevyAddress = hesMoving.getAddress();
			if(prevyAddress != null) {
				Set<Client> prevvies = prevyAddress.getResidents();
				prevvies.remove(hesMoving);
				prevyAddress.setResidents(prevvies);
			}
			repo.save(prevyAddress);
			hesMoving.setAddress(myDude);
			crepo.save(hesMoving);
		}
		//Set<Client> newRelations = 
		return repo.save(myDude).getId();
		// Street
		// City
		// State
	}
	
	public Long put(Long id, AddressDto addressDto) {
		addressDto.setId(id);
		Address newy = mapper.toEntity(addressDto);
		Address oldy = repo.getOne(id);
		for(Client clienty : oldy.getResidents()) {
			clienty.setAddress(newy);
			crepo.save(clienty);
		}
		for(Client clienty : newy.getResidents()) {
			Address hisOld = clienty.getAddress();
			if(hisOld != null) {
				Set<Client> oldiesRessies = hisOld.getResidents();
				oldiesRessies.remove(clienty);
				hisOld.setResidents(oldiesRessies);
				repo.save(hisOld);
			}
			clienty.setAddress(newy);
			crepo.save(clienty);
		}
		
		return repo.save(newy).getId();
	}
	
	public void delete(Long id) {
		Address oldy = repo.getOne(id);
		for(Client clienty : oldy.getResidents()) {
			clienty.setAddress(null);
			crepo.save(clienty);
		}
		oldy.setResidents(null);
		repo.save(oldy);
		repo.delete(oldy);
	}
	
	
	
}
