package com.skilldistillery.videostorerest.services;

import java.util.List;

import com.skilldistillery.jpavideostore.entities.Actor;
import com.skilldistillery.jpavideostore.entities.Film;

public interface FilmService {
	List<Film> index();
	Film show(int filmId);
	Film create(Film film);
	Film update(int filmId, Film film);
	boolean delete(int filmId);
	List<Actor> getActorsForFilm(int filmId);
}
