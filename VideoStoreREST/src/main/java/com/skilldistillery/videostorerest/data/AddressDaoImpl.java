package com.skilldistillery.videostorerest.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.jpavideostore.entities.Address;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class AddressDaoImpl implements AddressDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Address> findAll() {
		String jpql = "SELECT a FROM Address a";
		return em.createQuery(jpql, Address.class).getResultList();
	}

	@Override
	public Address findById(int addrId) {
		return em.find(Address.class, addrId);
	}

	@Override
	public Address create(Address address) {
		// TODO sanity check address properties
		em.persist(address);
		return address;
	}

}
