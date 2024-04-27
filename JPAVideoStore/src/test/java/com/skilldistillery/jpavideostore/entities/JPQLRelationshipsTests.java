package com.skilldistillery.jpavideostore.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jpavideostore.client.JPQLRelationshipsLab;

class JPQLRelationshipsTests {

	private JPQLRelationshipsLab lab;

	@BeforeEach
	void setUp() throws Exception {
		lab = new JPQLRelationshipsLab();
	}

	@AfterEach
	void tearDown() throws Exception {
		lab = null;
	}

	@Test
	void test_getStoresByState() {
		List<Store> stores = lab.getStoresByState("Washington");
		assertNotNull(stores);
		assertTrue(stores.size() > 0);
	}

	@Test
	void test_getStoresByState_returns_empty_list_for_invalid_state() {
		List<Store> stores = lab.getStoresByState("BLAHBLAHBLAH");
		assertNotNull(stores);
		assertTrue(stores.size() == 0);
	}

	@Test
	void test_getRentalsForCustomerWithCustomerId() {
		List<Rental> rentals = lab.getRentalsForCustomerWithCustomerId(1);
		assertNotNull(rentals);
		assertTrue(rentals.size() > 0);
	}

	@Test
	void test_getFilmsForActorWithId() {
		List<Film> films = lab.getFilmsForActorWithId(1);
		assertNotNull(films);
		assertTrue(films.size() > 0);
	}
	
	@Test
	void test_getNumberOfFilmsForCategoryWithName() {
		int count = lab.getNumberOfFilmsForCategoryWithName("Action");
		assertTrue(count > 0);
	}

	@Test
	void test_getNumberOfFilmsForCategoryWithName_returns_0_for_invalid_category() {
		int count = lab.getNumberOfFilmsForCategoryWithName("NOSUCHCATEGORY");
		assertTrue(count == 0);
	}

}
