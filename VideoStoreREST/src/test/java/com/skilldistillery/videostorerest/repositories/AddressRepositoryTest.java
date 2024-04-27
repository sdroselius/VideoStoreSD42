package com.skilldistillery.videostorerest.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.jpavideostore.entities.Address;

@SpringBootTest
public class AddressRepositoryTest {

	@Autowired
	private AddressRepository addressRepo;
	
	@Test
	public void test_findById() {
		Optional<Address> opt = addressRepo.findById(1);
		assertTrue(opt.isPresent());
		Address addr = opt.get();
		assertEquals("47 MySakila Drive",addr.getStreet());
	}
}
