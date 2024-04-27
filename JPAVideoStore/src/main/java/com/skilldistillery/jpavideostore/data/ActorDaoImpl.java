package com.skilldistillery.jpavideostore.data;

import com.skilldistillery.jpavideostore.entities.Actor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ActorDaoImpl implements ActorDAO {
	
	private static EntityManagerFactory emf 
	= Persistence.createEntityManagerFactory("VideoStore");

	@Override
	public Actor create(Actor actor) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(actor);
		em.getTransaction().commit();
		em.close();
		return null;
	}

	@Override
	public Actor update(int actorId, Actor actor) {
		EntityManager em = emf.createEntityManager();
		Actor managed = em.find(Actor.class, actorId);
		if (managed != null) {
			em.getTransaction().begin();
			managed.setFirstName(actor.getFirstName());
			managed.setLastName(actor.getLastName());
			em.flush();
			em.getTransaction().commit();
		}
		em.close();
		return managed;
	}

	@Override
	public boolean destroy(int id) {
		boolean deleted = false;
		EntityManager em = emf.createEntityManager();
		Actor managed = em.find(Actor.class, id);
		if (managed != null) {
			em.getTransaction().begin();
			em.remove(managed);
			em.getTransaction().commit();
			deleted = true;
		}
		em.close();
		return deleted;
	}

}