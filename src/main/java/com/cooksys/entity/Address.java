package com.cooksys.entity;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cooksys.dto.datatype.BaseEntity;
@Entity
public class Address implements BaseEntity<Long>{
	@Id
	@GeneratedValue
	private Long id;
	private String street;
	private String city;
	private String state;
	@OneToMany(mappedBy = "address")
	private Set<Client> residents;
	
	public Set<Client> getResidents() {
		return residents;
	}

	public void setResidents(Set<Client> residents) {
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
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}

}
