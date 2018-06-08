package com.cooksys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.dto.AddressDto;
import com.cooksys.dto.AddressDtoStripped;
import com.cooksys.dto.ClientDto;
import com.cooksys.dto.ClientDtoStripped;
import com.cooksys.service.ClientService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("user")
public class ClientController {
	private ClientService service;
	public ClientController(ClientService service) {
		this.service = service;
	}

	@GetMapping
	public List<ClientDtoStripped> getAll() {
		return service.getAll();
	}
	
	@GetMapping("{id}")
	public ClientDtoStripped get(@PathVariable Long id) {
		return service.get(id);
	}
	
	@GetMapping("{id}/address")
	public AddressDtoStripped getAddress(@PathVariable Long id){
		return service.getAddress(id);
	}
	
	@GetMapping("{id}/relations")
	public List<ClientDtoStripped> getRelations(@PathVariable Long id){
		return service.getRelations(id);
	}
	
	@PostMapping
	public Long post(@RequestBody @Validated ClientDto clientDto) {
		return service.post(clientDto);
	}
	
	@PostMapping("{id}/relations/{relationId}")
	public Long postRelation(@PathVariable Long id, @PathVariable Long relationId) {
		return service.postRelation(id, relationId);
	}
	
	@PostMapping("{id}/address/{addressId}")
	public Long postAddress(@PathVariable Long id, @PathVariable Long addressId) {
		return service.postAddress(id, addressId);
	}
	
	@PutMapping("{id}")
	public Long put(@PathVariable Long id, @RequestBody @Validated ClientDto clientDto) {
		return service.put(id, clientDto);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
