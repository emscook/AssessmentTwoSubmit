package com.cooksys.repository;
import java.util.List;
import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cooksys.entity.Client;
import com.cooksys.entity.Address;
public interface ClientRepository extends JpaRepository<Client, Long>{
	List<Client> findByAddressEquals(Address address);
	Long countByAddress(Address goodGuy);
}