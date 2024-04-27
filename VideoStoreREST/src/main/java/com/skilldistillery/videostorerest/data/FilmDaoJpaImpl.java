package com.skilldistillery.videostorerest.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.jpavideostore.entities.Film;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FilmDaoJpaImpl implements FilmDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Film> findAll() {
		String jpql = "SELECT f FROM Film f";
		return em.createQuery(jpql, Film.class).getResultList();
	}

}
