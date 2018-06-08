package com.cooksys.dto;

import java.util.Date;

import com.cooksys.dto.datatype.BaseEntity;
import com.cooksys.dto.datatype.Reference;
import com.cooksys.entity.Address;

public class ClientDto implements BaseEntity<Long>{
	private Long id;
	private Date birthday;	
	private String name;
	private String password;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Reference<Address, Long> address;
	public Reference<Address, Long> getAddress() {
		return address;
	}

	public void setAddress(Reference<Address, Long> address) {
		this.address = address;
	}

	
	
	
	
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		ClientDto other = (ClientDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
