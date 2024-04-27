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

class ActorTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Actor actor;

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
		actor = em.find(Actor.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		actor = null;
	}

	@Test
	void test_Actor_entity_mapping() {
		assertNotNull(actor);
		assertEquals("Penelope", actor.getFirstName());
	}

	@Test
	void test_Actor_Film_ManyToMany_mapping() {
		assertNotNull(actor);
		assertNotNull(actor.getFilms());
		assertTrue(actor.getFilms().size() > 0);
	}
	

	
}
