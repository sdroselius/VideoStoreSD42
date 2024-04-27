package com.skilldistillery.jpavideostore.client;

import com.skilldistillery.jpavideostore.data.ActorDAO;
import com.skilldistillery.jpavideostore.data.ActorDaoImpl;
import com.skilldistillery.jpavideostore.entities.Actor;

public class ActorDAOTest {

	public static void main(String[] args) {
		ActorDAO dao = new ActorDaoImpl();
		
		Actor newActor = new Actor("Troy", "McClure");
		
		System.out.println(newActor);
		dao.create(newActor);
		System.out.println(newActor);
		
	}

}