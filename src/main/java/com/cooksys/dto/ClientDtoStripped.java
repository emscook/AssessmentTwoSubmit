package com.cooksys.dto;

import java.util.Date;

import com.cooksys.dto.datatype.BaseEntity;

public class ClientDtoStripped{
	private Date birthday;	
	private String name;
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
	@Override
	public int hashCode() {
		return 1;
	}
	@Override
	public boolean equals(Object obj) {
		return true;
	}
	
}
