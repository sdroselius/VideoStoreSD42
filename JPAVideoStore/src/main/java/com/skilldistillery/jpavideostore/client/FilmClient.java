package com.skilldistillery.jpavideostore.client;

import java.util.Scanner;

import com.skilldistillery.jpavideostore.entities.Film;
import com.skilldistillery.jpavideostore.entities.Language;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FilmClient {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoStore");
		EntityManager em = emf.createEntityManager();
		Scanner kb = new Scanner(System.in);

		
		Film film = new Film();
		film.setTitle("Something");
		film.setDescription("A description");

		em.getTransaction().begin();
		Language lang = em.find(Language.class,1);
		film.setLanguage(lang);   // sets the foreign key language_id to 1 when persisted
		em.persist(film);
		
		em.getTransaction().commit();
		
//		Film film = em.find(Film.class, 1);
//		
////		System.out.println("Before film.toString");
////		kb.nextLine();
//		System.out.println(film);
//		System.out.println(film.getCategories());
//		
////		Category newCat = new Category();
////		newCat.setId(2);
//		Category newCat = em.find(Category.class, 2);
//		
//		film.addCategory(newCat);
		
//
//		System.out.println("Before film.getActors");
//		kb.nextLine();
//		System.out.println(film.getActors());
//		
//		System.out.println("Before film.getActors.get");
//		kb.nextLine();
//		Actor penelope = film.getActors().get(0);
//		
//		System.out.println("Before actor.toString");
//		kb.nextLine();
//		System.out.println(penelope);
//		penelope.getFilms().size();		
//		System.out.println("Before actor.getFilms");
//		kb.nextLine();
//		System.out.println(penelope.getFilms());
		
		em.close();
		emf.close();
		
		kb.close();
	}
}