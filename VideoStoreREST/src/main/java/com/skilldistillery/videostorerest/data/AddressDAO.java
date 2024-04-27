package com.skilldistillery.videostorerest.data;

import java.util.List;

import com.skilldistillery.jpavideostore.entities.Address;

public interface AddressDAO {
	List<Address> findAll();
	Address findById(int addrId);
	Address create(Address address);
}
