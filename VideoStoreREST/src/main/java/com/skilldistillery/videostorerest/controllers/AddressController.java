package com.skilldistillery.videostorerest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jpavideostore.entities.Address;
import com.skilldistillery.videostorerest.data.AddressDAO;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class AddressController {
	
	@Autowired
	private AddressDAO addressDao;
	
	@GetMapping("addresses")
	public List<Address> index(){
		  return addressDao.findAll();
	}

	@GetMapping("addresses/{id}")
	public Address show(@PathVariable("id") Integer addrId, HttpServletResponse res){
		Address address = addressDao.findById(addrId);
		if (address == null) {
			res.setStatus(404);
		}
		return address;
	}
	
	@PostMapping("addresses")
	public Address addAddress(@RequestBody Address address, HttpServletResponse res ) {
		try {
			address = addressDao.create(address);
			res.setStatus(201);
			res.setHeader("Location", "http://localhost:8081/api/addresses/" + address.getId());
		} catch (Exception e) {
			res.setStatus(400);
			address = null;
			e.printStackTrace();
		}
		return address;
	}
	
}
