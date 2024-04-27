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

class FilmTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Film film;

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
		film = em.find(Film.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		film = null;
	}

	@Test
	void test_Film_entity_mapping() {
		assertNotNull(film);
		assertEquals("ACADEMY DINOSAUR", film.getTitle());
		assertEquals(1993, film.getReleaseYear());
		assertEquals(0.99, film.getRentalRate());
		assertEquals(20.99, film.getReplacementCost());
	}

	@Test
	void test_Film_Rating_enum() {
		assertNotNull(film);
		assertNotNull(film.getRating());
		assertEquals(Rating.PG, film.getRating());
	}
	
	@Test
	void test_Film_Language_ManyToOne() {
		assertNotNull(film);
		assertNotNull(film.getLanguage());
		assertEquals("Japanese", film.getLanguage().getName());
	}
	
	@Test
	void test_Film_Actor_ManyToMany() {
		assertNotNull(film);
		assertNotNull(film.getActors());
		assertTrue(film.getActors().size() > 0); 
	}
	
	@Test
	void test_Film_Category_ManyToMany() {
		assertNotNull(film);
		assertNotNull(film.getCategories());
		assertTrue(film.getCategories().size() > 0); 
	}
	
	@Test
	void test_Film_InventoryItem_OneToMany() {
		assertNotNull(film);
		assertNotNull(film.getInventoryItems());
		assertTrue(film.getInventoryItems().size() > 0); 
	}
	
}
