package com.skilldistillery.bootmvc.data;

import java.util.List;

import com.skilldistillery.jpavideostore.entities.Film;

public interface FilmDAO {
	Film findById(int filmId);
	List<Film> findAll();
	List<Film> findAllSortByYear();
}
