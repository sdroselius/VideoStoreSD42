package com.skilldistillery.jpavideostore.client;

import java.util.List;

import com.skilldistillery.jpavideostore.entities.Actor;
import com.skilldistillery.jpavideostore.entities.Category;
import com.skilldistillery.jpavideostore.entities.Customer;
import com.skilldistillery.jpavideostore.entities.Film;
import com.skilldistillery.jpavideostore.entities.Rental;
import com.skilldistillery.jpavideostore.entities.Store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPQLRelationshipsLab {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoStore");

	public List<Store> getStoresByState(String state) {
		EntityManager em = emf.createEntityManager();
		String jpql = "SELECT s FROM Store s WHERE s.address.state = :st";
		List<Store> stores = em.createQuery(jpql, Store.class).setParameter("st", state).getResultList();
		em.close();
		return stores;
	}

	public List<Rental> getRentalsForCustomerWithCustomerId(int id) {
		EntityManager em = emf.createEntityManager();
		List<Rental> rentals;

//		Customer cust = em.find(Customer.class, id);
//		rentals = cust.getRentals();

		String jpql = "SELECT r FROM Rental r WHERE r.customer.id = :custId";
		rentals = em.createQuery(jpql, Rental.class).setParameter("custId", id).getResultList();

//		String jpql = "SELECT c.rentals FROM Customer c WHERE c.id = :custId";
//		rentals = em.createQuery(jpql, Rental.class)
//					.setParameter("custId", id)
//				    .getResultList();

		em.close();
		return rentals;
	}

	public List<Film> getFilmsForActorWithId(int id) {
		EntityManager em = emf.createEntityManager();
		List<Film> films = null;

//		Actor actor = em.find(Actor.class, id);
//		if (actor != null) {
//			films = actor.getFilms();
////			films.size();
//		}
		
//		String jpql = "SELECT a.films FROM Actor a WHERE a.id = :actorId";
//		String jpql = "SELECT f FROM Film f WHERE f.actor.id = :actorId"; //NO
//		String jpql = "SELECT f FROM Film f WHERE f.actors.id = :actorId"; //NOPE
		String jpql = "SELECT f FROM Film f JOIN f.actors a WHERE a.id = :actorId";
		films = em.createQuery(jpql, Film.class)
				  .setParameter("actorId", id)
				  .getResultList();

		em.close();
		return films;
	}

	
	public int getNumberOfFilmsForCategoryWithName(String name) {
		EntityManager em = emf.createEntityManager();
		int count = 0;
//		String jpql = "SELECT COUNT(f) FROM Film f JOIN f.categories cat WHERE cat.name = :nm";

		String jpql = "SELECT cat FROM Category cat WHERE cat.name = :nm";
		try {
			Category cat = em.createQuery(jpql, Category.class)
					         .setParameter("nm", name)
					         .getSingleResult();
			count = cat.getFilms().size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		em.close();
		return count;
	}
	
}
