package com.skilldistillery.jpavideostore.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class StaffTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Staff emp;

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
		emp = em.find(Staff.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		emp = null;
	}

	@Test
	void test_Staff_entity_mapping() {
		assertNotNull(emp);
		assertEquals("Larry", emp.getFirstName());
		assertEquals("Kong", emp.getLastName());
	}

	@Test
	void test_Staff_Address_ManyToOne_mapping() {
		assertNotNull(emp);
		assertNotNull(emp.getAddress());
		assertEquals("Las Vegas", emp.getAddress().getCity());
	}
	
	@Test
	void test_Staff_Store_ManyToOne_mapping() {
		assertNotNull(emp);
		assertNotNull(emp.getStore());
		assertEquals(4, emp.getStore().getId());
	}
	
	@Test
	void test_Staff_Supervisor_ManyToOne_mapping_returns_null_for_boss() {
		assertNotNull(emp);
		assertNull(emp.getSupervisor());
	}
	
	@Test
	void test_Staff_Supervisor_ManyToOne_mapping() {
		emp = em.find(Staff.class, 14);
		assertNotNull(emp);
		assertNotNull(emp.getSupervisor());
		assertEquals(7, emp.getSupervisor().getId());
	}
	
	@Test
	void test_Staff_Supervisees_OneToMany_mapping() {
		assertNotNull(emp);
		assertNotNull(emp.getSupervisees());
		assertTrue(emp.getSupervisees().size() > 0);
	}
	
	
}
