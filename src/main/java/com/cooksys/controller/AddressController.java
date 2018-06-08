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
import com.cooksys.dto.ClientDto;
import com.cooksys.dto.ClientDtoStripped;
import com.cooksys.dto.AddressDto;
import com.cooksys.dto.AddressDtoStripped;
import com.cooksys.service.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("address")
@Api
public class AddressController {

	private AddressService service;
	
	AddressController(AddressService service){
		this.service = service;
	}
	
	@GetMapping
	public List<AddressDtoStripped> getAll(@RequestParam(value = "city", required = false) String city,
									@RequestParam(value = "state", required = false) String state) {
		return service.getAddress(city, state);
	}
	
	@GetMapping("{id}")
	public AddressDtoStripped get(@PathVariable Long id) {
		return service.get(id);
	}
	
	@GetMapping("{id}/residents")
	public List<ClientDtoStripped> getResidents(@PathVariable Long id){
		return service.getResidents(id);
	}
	
	@PostMapping	
	public Long post(@RequestBody @Validated AddressDto addressDto) {
		return service.post(addressDto);
	}
	
	@PutMapping("{id}")
	public Long put(@PathVariable Long id, @RequestBody @Validated AddressDto addressDto) {
		return service.put(id, addressDto);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
