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

class InventoryItemTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private InventoryItem item;

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
		item = em.find(InventoryItem.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		item = null;
	}

//	+----+---------+----------+-----------------+---------------------+
//	| id | film_id | store_id | media_condition | last_update         |
//	+----+---------+----------+-----------------+---------------------+
//	|  1 |       1 |        1 | Used            | 2016-09-22 14:16:36 |
//	+----+---------+----------+-----------------+---------------------+

	@Test
	void test_InventoryItem_entity_mapping() {
		assertNotNull(item);
		assertEquals(2016, item.getLastUpdate().getYear());
//		assertEquals(0b1001, item.getLastUpdate().getMonthValue());
		assertEquals(9, item.getLastUpdate().getMonthValue());
		assertEquals(MediaCondition.Used, item.getMediaCondition());
	}

	@Test
	void test_InventoryItem_Film_ManyToOne_mapping() {
		assertNotNull(item);
		assertNotNull(item.getFilm());
		assertEquals(1, item.getFilm().getId());
	}
	
	@Test
	void test_InventoryItem_Store_ManyToOne_mapping() {
		assertNotNull(item);
		assertNotNull(item.getStore());
		assertEquals(1, item.getStore().getId());
	}
	
	@Test
	void test_InventoryItem_Rental_OneToMany_mapping() {
		assertNotNull(item);
		assertNotNull(item.getRentals());
		assertTrue(item.getRentals().size() > 0);
	}
	
}
