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

class StoreTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Store store;

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
		store = em.find(Store.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		store = null;
	}

	@Test
	void test_Store_entity_mapping() {
		assertNotNull(store);
		assertNotNull(store.getAddress());
		assertEquals("Seattle", store.getAddress().getCity());
	}

	@Test
	void test_Store_Staff_OneToOne_relationship() {
		assertNotNull(store);
		assertNotNull(store.getManager());
		assertEquals("Dutch", store.getManager().getFirstName());
		assertEquals("LaFever", store.getManager().getLastName());
	}
	
	@Test
	void test_Store_Customer_OneToMany_relationship() {
		assertNotNull(store);
		assertNotNull(store.getCustomers());
		assertTrue(store.getCustomers().size() > 0);
	}
	
	@Test
	void test_Store_Staff_OneToMany_relationship() {
		assertNotNull(store);
		assertNotNull(store.getEmployees());
		assertTrue(store.getEmployees().size() > 0);
	}
	
	@Test
	void test_Store_InventoryItem_OneToMany_relationship() {
		assertNotNull(store);
		assertNotNull(store.getInventoryItems());
		assertTrue(store.getInventoryItems().size() > 0);
	}
	
}
