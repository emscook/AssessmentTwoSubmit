package com.cooksys.dto;

import com.cooksys.dto.datatype.BaseEntity;

public class AddressDtoStripped {
	private String street;
	private String city;
	private String state;
	
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
	public int hashCode() {
		//final int prime = 31;
		//int result = 1;
		//result = prime * result + ((id == null) ? 0 : id.hashCode());
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressDtoStripped other = (AddressDtoStripped) obj;
		//if (id == null) {
		//	if (other.id != null)
		//		return false;
		//} else if (!id.equals(other.id))
		//	return false;
		return true;
	}
}
