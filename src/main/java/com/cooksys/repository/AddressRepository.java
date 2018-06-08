package com.cooksys.repository;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cooksys.entity.Address;
import com.cooksys.entity.Client;
public interface AddressRepository extends JpaRepository<Address, Long>{
	//List<ProjectManager> findByProjectsDueDateLessThan(Date eck);
	//List<ProjectManager> findByProjectsContains(Client eck);
	List<Address> findByCity(String city);
	List<Address> findByState(String state);
	List<Address> findByCityAndStateEquals(String city, String state);	
}
	