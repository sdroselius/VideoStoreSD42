package com.skilldistillery.videostorerest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jpavideostore.entities.Film;
import com.skilldistillery.jpavideostore.entities.Language;
import com.skilldistillery.jpavideostore.entities.Rating;

public interface FilmRepository extends JpaRepository<Film, Integer> {
	Film searchById(int filmId);
	
//	Write a query method that will return a film by its title.
	Film findByTitle(String title);
	
//	Write a query method that will return a collection of films by their release year.
	List<Film> findByReleaseYear(int year);
//	Write a query method that will return the number of films for a release year.
	long countByReleaseYear(int year);

//	Write a query method that will determine if a film exists by its title.
	boolean existsByTitle(String title);

//	Write a query method that will determine if any films exists for a release year.
	boolean existsByReleaseYear(int year);

//	Write a query method that will return a collection of films by their rating.
	List<Film> findByRating(Rating rating);

//	Write a query method that will return a collection of films by their language.
	List<Film> findByLanguage(Language lang);

//	Write a query method that will return a collection of films by the name of their language.
	List<Film> findByLanguage_Name(String lang);
	List<Film> findFilmsByLanguage_Name(String lang);

//	Write a query method that will return a collection of films by the name of their language as well as their release year.
	List<Film> findByLanguage_NameAndReleaseYear(String lang, int year);

//	Write a query method that will return a collection of films that have replacement costs within a specific range.
	List<Film> findByReplacementCostGreaterThanEqualAndReplacementCostLessThanEqual(double min, double max);
	List<Film> findByReplacementCostBetween(double min, double max);

}
