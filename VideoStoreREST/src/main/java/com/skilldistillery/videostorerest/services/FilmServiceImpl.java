package com.skilldistillery.videostorerest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jpavideostore.entities.Actor;
import com.skilldistillery.jpavideostore.entities.Film;
import com.skilldistillery.jpavideostore.entities.Language;
import com.skilldistillery.videostorerest.repositories.ActorRepository;
import com.skilldistillery.videostorerest.repositories.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmRepository filmRepo;
	
	@Autowired
	private ActorRepository actorRepo;
	
	@Override
	public List<Film> index() {
		return filmRepo.findAll();
	}

	@Override
	public Film show(int filmId) {
		return filmRepo.searchById(filmId);
	}

	@Override
	public Film create(Film film) {
		if (film.getLanguage() == null) {
			Language lang = new Language();
			lang.setId(1);
			film.setLanguage(lang);
		}
		return filmRepo.saveAndFlush(film);
	}

	@Override
	public Film update(int filmId, Film film) {
		Film existing = filmRepo.searchById(filmId);
		if (existing != null) {
			if (film.getLanguage() != null) {
				existing.setLanguage(film.getLanguage());
			}
			existing.setLength(film.getLength());
			existing.setRating(film.getRating());
			existing.setReleaseYear(film.getReleaseYear());
			existing.setRentalDuration(film.getRentalDuration());
			existing.setRentalRate(film.getRentalRate());
			existing.setReplacementCost(film.getReplacementCost());
			existing.setTitle(film.getTitle());
			return filmRepo.saveAndFlush(existing);
		}
		return null;
	}

	@Override
	public boolean delete(int filmId) {
		boolean deleted = false;
		if (filmRepo.existsById(filmId)) {
			filmRepo.deleteById(filmId);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public List<Actor> getActorsForFilm(int filmId) {
		if ( ! filmRepo.existsById(filmId) ) {
			return null;
		}
		return actorRepo.findByFilms_Id(filmId);
	}
	
	

}
