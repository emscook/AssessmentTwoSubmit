package com.cooksys.dto;

import java.util.Set;
import com.cooksys.dto.datatype.BaseEntity;
import com.cooksys.dto.datatype.Reference;
import com.cooksys.entity.Client;

public class AddressDto implements BaseEntity<Long>{
	private Long id;
	private String street;
	private String city;
	private String state;
	private Set<Reference<Client, Long>> residents;
	
	public Set<Reference<Client, Long>> getResidents() {
		return residents;
	}

	public void setResidents(Set<Reference<Client, Long>> residents) {
		this.residents = residents;
	}
	
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressDto other = (AddressDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
