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

class RentalTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Rental rental;

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
		rental = em.find(Rental.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		rental = null;
	}

//	+----+---------------------+--------------+-------------+---------------------+----------+
//	| id | rental_date         | inventory_id | customer_id | return_date         | staff_id |
//	+----+---------------------+--------------+-------------+---------------------+----------+
//	|  1 | 2014-05-24 22:53:30 |        14072 |         130 | 2014-05-26 22:04:30 |       46 |
//	+----+---------------------+--------------+-------------+---------------------+----------+
	
	@Test
	void test_Rental_entity_mapping() {
		assertNotNull(rental);
	}

	@Test
	void test_Rental_temporal_mappings() {
		assertNotNull(rental);
		assertNotNull(rental.getRentalDate());
		assertEquals(2014, rental.getRentalDate().getYear());
		assertEquals(5, rental.getRentalDate().getMonthValue());
		assertNotNull(rental.getReturnDate());
		assertEquals(2014, rental.getReturnDate().getYear());
		assertEquals(5, rental.getReturnDate().getMonthValue());
	}
	
	@Test
	void test_Rental_Staff_ManyToOne_mapping() {
		assertNotNull(rental);
		assertNotNull(rental.getStaff());
		assertEquals(46, rental.getStaff().getId());
	}
	
	@Test
	void test_Rental_Customer_ManyToOne_mapping() {
		assertNotNull(rental);
		assertNotNull(rental.getCustomer());
		assertEquals(130, rental.getCustomer().getId());
	}
	
	@Test
	void test_Rental_InventoryItem_ManyToOne_mapping() {
		assertNotNull(rental);
		assertNotNull(rental.getInventoryItem());
		assertEquals(14072, rental.getInventoryItem().getId());
	}
	
	@Test
	void test_Rental_Payment_OneToMany_mapping() {
		assertNotNull(rental);
		assertNotNull(rental.getPayments());
		assertTrue(rental.getPayments().size() > 0);
	}
	
}
