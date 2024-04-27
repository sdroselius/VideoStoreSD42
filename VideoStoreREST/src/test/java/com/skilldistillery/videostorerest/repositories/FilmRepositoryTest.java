package com.skilldistillery.videostorerest.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.jpavideostore.entities.Film;
import com.skilldistillery.jpavideostore.entities.Language;
import com.skilldistillery.jpavideostore.entities.Rating;

@SpringBootTest
public class FilmRepositoryTest {

	@Autowired
	private FilmRepository repo;
	
	@Test
	void test_findByTitle(){
		Film f = repo.findByTitle("ACE GOLDFINGER");
		assertNotNull(f);
		assertEquals(2, f.getId());
	}
	@Test
	void test_findByReleaseYear(){
		List<Film> films = repo.findByReleaseYear(2012);
		assertNotNull(films);
		assertTrue(films.size() > 0);
	}
	
	@Test
	void test_countByReleaseYear(){
		long num = repo.countByReleaseYear(2012);
		assertTrue(num > 0);
		assertEquals(31, num);
	}
	
	@Test
	void test_existsByTitle(){
		boolean result = repo.existsByTitle("ACE GOLDFINGER");
		assertTrue(result);
	}
	
	@Test
	void test_existsByTitle_returns_false_for_invalid_title(){
		boolean result = repo.existsByTitle("xXxNO SUCH FILMxXx");
		assertFalse(result);
	}
	
	@Test
	void test_existsByReleaseYear(){
		boolean result = repo.existsByReleaseYear(2012);
		assertTrue(result);
	}
	
	@Test
	void test_existsByReleaseYear_returns_false_for_invalid_year(){
		boolean result = repo.existsByReleaseYear(1776);
		assertFalse(result);
	}
	
	@Test
	void test_findByRating(){
		List<Film> films = repo.findByRating(Rating.G);
		assertNotNull(films);
		assertTrue(films.size() > 0);
	}
	
	@Test
	void test_findByLanguage(){
		Language l = new Language();
		l.setId(3);
		List<Film> films = repo.findByLanguage(l);
		assertNotNull(films);
		assertTrue(films.size() > 0);
	}
	
	@Test
	void test_findByLanguage_Name(){
		List<Film> films = repo.findByLanguage_Name("Japanese");
		assertNotNull(films);
		assertTrue(films.size() > 0);
	}
	
	@Test
	void test_findFilmsByLanguage_Name(){
		List<Film> films = repo.findFilmsByLanguage_Name("Japanese");
		assertNotNull(films);
		assertTrue(films.size() > 0);
	}
	
	@Test
	void test_findByLanguage_NameAndReleaseYear(){
		List<Film> films = repo.findByLanguage_NameAndReleaseYear("Japanese", 2006);
		assertNotNull(films);
		assertTrue(films.size() > 0);
	}
	

	@Test
	void test_findByReplacementCostGreaterThanEqualAndReplacementCostLessThanEqual(){
		List<Film> films = repo.findByReplacementCostGreaterThanEqualAndReplacementCostLessThanEqual(10.0,12.0);
		assertNotNull(films);
		assertTrue(films.size() > 0);
	}
	
	@Test
	void test_findByReplacementCostBetween(){
		List<Film> films = repo.findByReplacementCostBetween(10.0,12.0);
		assertNotNull(films);
		assertTrue(films.size() > 0);
	}
	
	
	
}
