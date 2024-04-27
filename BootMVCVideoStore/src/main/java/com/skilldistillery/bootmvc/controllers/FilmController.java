package com.skilldistillery.bootmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.bootmvc.data.FilmDAO;
import com.skilldistillery.jpavideostore.entities.Film;

@Controller
public class FilmController {

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = { "/", "home.do" })
	public String index(Model model) {
		model.addAttribute("filmList", filmDao.findAll());
//		model.addAttribute("filmList", filmDao.findAllSortByYear());
		return "home";
	}

	@RequestMapping(path = "getFilm.do")
	public String showFilm(@RequestParam("filmId") Integer filmId, Model model) {
		Film film = filmDao.findById(filmId);
		model.addAttribute("film", film);
		return "film/show";
	}

//	@RequestMapping(path = "getFilm.do")
//	public ModelAndView showFilm(@RequestParam("filmId") Integer filmId) {
//		Film film = filmDao.findById(filmId);
//		ModelAndView mv = new ModelAndView("film/show");
//		mv.addObject("film", film);
//		return mv;
//	}

}
