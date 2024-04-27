package com.skilldistillery.jpavideostore.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class AddressTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Address addr;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("VideoStore");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		addr = em.find(Address.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		addr = null;
	}

	@Test
	void test_Address_entity_mapping() {
		assertNotNull(addr);
		assertEquals("Lethbridge", addr.getCity());
	}

	@Test
	void test_Address_Country_ManyToOne_relationship_mapping() {
		assertNotNull(addr);
		assertNotNull(addr.getCountry());
		assertEquals("Canada", addr.getCountry().getName());
	}
	
}
