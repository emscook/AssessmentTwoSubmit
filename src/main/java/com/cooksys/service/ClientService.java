package com.cooksys.service;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cooksys.dto.AddressDto;
import com.cooksys.dto.AddressDtoStripped;
import com.cooksys.dto.ClientDto;
import com.cooksys.dto.ClientDtoStripped;
import com.cooksys.entity.Client;
import com.cooksys.entity.Address;
import com.cooksys.exception.ReferencedEntityNotFoundException;
import com.cooksys.mapper.AddressMapper;
import com.cooksys.mapper.ClientMapper;
import com.cooksys.repository.AddressRepository;
//import com.cooksys.repository.ProjectManagerRepository;
import com.cooksys.repository.ClientRepository;

@Service
public class ClientService {
	private ClientRepository repo;
	private AddressRepository addressRepo;
	private ClientMapper mapper;
	private AddressMapper addyMapper;
	public ClientService(ClientRepository repo, ClientMapper mapper,AddressRepository addressRepo, AddressMapper addyMapper) {// 
		this.repo = repo;
		this.mapper = mapper;
		this.addressRepo = addressRepo;
		this.addyMapper = addyMapper;
	}
	
	public List<ClientDtoStripped> getAll() {
		return repo.findAll().stream().map(mapper::toStripped).collect(Collectors.toList());
	}
	public AddressDtoStripped getAddress(Long id) {
		return addyMapper.toStripped(repo.getOne(id).getAddress());
	}
	public List<ClientDtoStripped> getRelations(Long id) {
		return repo.getOne(id).getRelations().stream().map(mapper::toStripped).collect(Collectors.toList());
	}
	public Long post(ClientDto clientDto) {
		clientDto.setId(null);
		Long reID = repo.save(mapper.toEntity(clientDto)).getId();
		Address fug = repo.getOne(reID).getAddress();
		fug.getResidents().add(repo.getOne(reID));
		addressRepo.save(fug);
		return reID;
	}
	
	public ClientDtoStripped get(Long id) {
		mustExist(id);
		//return mapper.toDto(repo.findOne(id));
		return mapper.toStripped(repo.getOne(id));
	}
	
	public Long postRelation(Long origin, Long destination) {
		if((!has(origin)) || (!has(destination))){
			return null;
		}
		Client a = repo.getOne(origin);
		Set<Client> aRel = a.getRelations();
		
		Client b = repo.getOne(destination);
		aRel.add(b);
		
		Set<Client> bRel = b.getRelations();
		bRel.add(a);
		a.setRelations(aRel);
		b.setRelations(bRel);
		repo.save(a);
		repo.save(b);
		return a.getId();
	}
	public Long postAddress(Long origin, Long destination) {
		if((!has(origin)) || (!addressRepo.exists(destination))){
			return null;
		}
		
		Client a = repo.getOne(origin);
		
		Address b = addressRepo.getOne(destination);
		a.setAddress(b);
		Set<Client> peeps = b.getResidents();
		peeps.add(a);
		b.setResidents(peeps);
		repo.save(a);
		addressRepo.save(b);
		return a.getId();
	}
	
	private void mustExist(Long id) {
		if(!has(id))
			throw new ReferencedEntityNotFoundException(Client.class, id);
	}
	
	public boolean has(Long id) {
		return repo.exists(id);
	}
	
	public Long put(Long id, ClientDto clientDto) {
		mustExist(id);
		clientDto.setId(id);
		Client oldGuy = repo.getOne(id);
		Set<Client> oldRelations = oldGuy.getRelations();
		Address paro = oldGuy.getAddress();
		if(oldRelations != null && oldRelations.size() > 0) {
			for(Client relation : oldRelations) {
				Set<Client> oldThings = relation.getRelations();
				oldThings.remove(relation);
				relation.setRelations(oldThings);
				repo.save(relation);
			}
		}
		if(paro != null) {
			Set<Client> oldResidents = paro.getResidents();
			oldResidents.remove(oldGuy);
			paro.setResidents(oldResidents);
			addressRepo.save(paro);
		}
		Client aha = mapper.toEntity(clientDto);
		Address hisPlace = aha.getAddress();
		if(hisPlace != null) {
			Set<Client> addRes = hisPlace.getResidents();
			addRes.add(aha);
		}
		Set<Client> newRelations = aha.getRelations();
		if(newRelations != null) {
			for(Client newFam : newRelations) {
				Set<Client> theirs = newFam.getRelations();
				theirs.add(aha);
				newFam.setRelations(theirs);
				repo.save(newFam);
			}
		}
		return repo.save(aha).getId();
	}
	
	public void delete(Long id) {
		mustExist(id);
		//clientDto.setId(id);
		Client oldGuy = repo.getOne(id);
		Set<Client> oldRelations = oldGuy.getRelations();
		
		if(oldRelations != null && oldRelations.size() > 0) {
			for(Client relation : oldRelations) {
				System.out.println(relation.getName());
				Set<Client> oldThings = relation.getRelations();
				oldThings.remove(oldGuy);
				relation.setRelations(oldThings);
				repo.save(relation);
			}
		}
		oldRelations = new HashSet<Client>();
		oldGuy.setRelations(oldRelations);
		//oldGuy.setRelations(null);
		Address paro = oldGuy.getAddress();
		if(paro != null) {
			Set<Client> oldResidents = paro.getResidents();
			oldResidents.remove(oldGuy);
			paro.setResidents(oldResidents);
			addressRepo.save(paro);
		}
		repo.save(oldGuy);
		repo.delete(id);
	}	
	//public List<ClientDto> getAllPastDue() {
	//	return repo.findByDueDateLessThan(new Date()).stream().map(mapper::toDto).collect(Collectors.toList());
	//}
	
	
}
