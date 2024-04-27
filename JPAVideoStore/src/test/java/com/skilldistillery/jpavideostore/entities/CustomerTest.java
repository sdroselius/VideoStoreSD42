package com.skilldistillery.jpavideostore.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class CustomerTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Customer cust;

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
		cust = em.find(Customer.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		cust = null;
	}

	@Test
	void test_Customer_entity_mapping() {
		assertNotNull(cust);
		assertEquals("Mary", cust.getFirstName());
		assertEquals("Smithers", cust.getLastName());
	}

	@Test
	void test_Customer_temporal_mappings() {
		assertNotNull(cust);
		assertNotNull(cust.getCreatedAt());
		assertEquals(2014, cust.getCreatedAt().getYear());
		assertEquals(5, cust.getCreatedAt().getMonthValue());
		assertNotNull(cust.getLastUpdate());
		assertEquals(2016, cust.getLastUpdate().getYear());
		assertEquals(9, cust.getLastUpdate().getMonthValue());
	}

	@Test
	void test_Customer_Address_OneToOne_mapping() {
		Customer cust = em.find(Customer.class, 1);
		assertNotNull(cust);
		assertNotNull(cust.getAddress());
		assertEquals(5, cust.getAddress().getId());
		assertEquals("1913 Hanoi Way", cust.getAddress().getStreet());
		assertEquals("Sasebo", cust.getAddress().getCity());
		assertEquals("35200", cust.getAddress().getPostalCode());
	}

	@Test
	void test_Customer_Rental_OneToMany_mapping() {
		assertNotNull(cust);
		assertNotNull(cust.getRentals());
		assertTrue(cust.getRentals().size() > 0);
	}

	@Test
	void test_Customer_Store_ManyToOne_mapping() {
		assertNotNull(cust);
		assertNotNull(cust.getStore());
		assertEquals(7, cust.getStore().getId());
	}
}
