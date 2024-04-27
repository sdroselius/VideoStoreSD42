package com.skilldistillery.videostorerest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jpavideostore.entities.Actor;
import com.skilldistillery.jpavideostore.entities.Film;
import com.skilldistillery.videostorerest.services.FilmService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class FilmController {

//	@Autowired
//	private FilmDAO filmDao;
	
	@Autowired
	private FilmService filmService;
	
	@GetMapping("films")
	public List<Film> index() {
//		return filmDao.findAll();
		return filmService.index();
	}
	
	@GetMapping("films/{filmId}")
	public Film getFilm(@PathVariable("filmId") Integer filmId, HttpServletResponse res) {
		Film film = filmService.show(filmId);
		if (film == null) {
			res.setStatus(404);
		}
		return film;
	}
	
	@PostMapping("films")
	public Film create(@RequestBody Film film, HttpServletRequest req, HttpServletResponse res) {
		try {
			film = filmService.create(film);
			res.setStatus(201);
			res.setHeader("Location", req.getRequestURL().append("/").append(film.getId()).toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			film = null;
		}
		return film;
	}
	
	@PutMapping("films/{filmId}")
	public Film updateFilm(
			@PathVariable("filmId") Integer filmId,
			@RequestBody Film film,
			HttpServletResponse res
	) {
		try {
			film = filmService.update(filmId, film);
			if (film == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			film = null;
		}
		return film;
	}
	
	@DeleteMapping("films/{filmId}")
	public void deleteFilm(
			@PathVariable("filmId") Integer filmId,
			HttpServletResponse res
	) {
		try {
			if (filmService.delete(filmId)) {
				res.setStatus(204);
			}
			else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		
	}
	
	@GetMapping("films/{filmId}/actors")
	public List<Actor> listActorsForFilm(
			@PathVariable("filmId") Integer filmId,
			HttpServletResponse res
	){
		List<Actor> cast = filmService.getActorsForFilm(filmId);
		if (cast == null) {
			res.setStatus(404);
		}
		return cast;
	}
	
}
