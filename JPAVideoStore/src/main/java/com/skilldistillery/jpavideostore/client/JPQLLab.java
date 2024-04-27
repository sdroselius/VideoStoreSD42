package com.skilldistillery.jpavideostore.client;

import java.util.List;

import com.skilldistillery.jpavideostore.entities.Customer;
import com.skilldistillery.jpavideostore.entities.Film;
import com.skilldistillery.jpavideostore.entities.Staff;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPQLLab {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoStore");
	
	public List<Customer> getRangeOfCustomers(int lowId, int highId) {
		EntityManager em = emf.createEntityManager();
		List<Customer> customers = null;
		String jpql = "SELECT c FROM Customer c WHERE c.id BETWEEN :low AND :hi";
		customers = em.createQuery(jpql, Customer.class)
				      .setParameter("low", lowId)
				      .setParameter("hi", highId)
				      .getResultList();
		
		em.close();
		return customers;
	}
	
	public String getCustomerEmailByName(String first, String last) {
		EntityManager em = emf.createEntityManager();
		String jpql = "SELECT c.email FROM Customer c WHERE c.firstName = :f AND c.lastName = :l";
		List<String> emails = em.createQuery(jpql, String.class)
				                .setParameter("f", first)
				                .setParameter("l", last)
				                .getResultList();
		
		em.close();
		return emails.get(0);
	}
	
	public Film getFilmByTitle(String title) {
		EntityManager em = emf.createEntityManager();
		Film film = null;
		
		String jpql = "SELECT f FROM Film f WHERE f.title = :t";
//		List<Film> films = em.createQuery(jpql, Film.class)
//				             .setParameter("t", title)
//				             .getResultList();
//		if (films.size() > 0) {
//		   film = films.get(0);
//		}
		
		try {
			film = em.createQuery(jpql, Film.class)
					             .setParameter("t", title)
					             .getSingleResult();
		} catch (Exception e) {
			System.err.println("No film found for title " + title);
		}
		
		em.close();
		return film;
	}

	public List<String> getFilmsTitlesByReleaseYear(int year) {
		EntityManager em = emf.createEntityManager();
		List<String> titles = null;
		String jpql = "SELECT f.title FROM Film f WHERE f.releaseYear = :y";
		titles = em.createQuery(jpql, String.class)
				   .setParameter("y", year)
				   .getResultList();
		em.close();
		return titles;
	}
	
	public Staff getActiveStaffByUsername(String username) {
		EntityManager em = emf.createEntityManager();
		Staff employee = null;
		String jpql = "SELECT e FROM Staff e WHERE e.username = :un AND e.active = true";
		try {
			employee = em.createQuery(jpql, Staff.class)
					     .setParameter("un", username)
					     .getSingleResult();
		} catch (Exception e) {
//			e.printStackTrace();
		}
		em.close();
		return employee;
	}
}