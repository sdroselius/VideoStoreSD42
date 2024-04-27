package com.skilldistillery.jpavideostore.client;

import com.skilldistillery.jpavideostore.entities.Staff;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StaffClient {
  public static void main(String[] args) {
    EntityManagerFactory emf =
      Persistence.createEntityManagerFactory("VideoStore");
    EntityManager em = emf.createEntityManager();

    Staff emp = em.find(Staff.class, 1);
    System.out.println(emp);

    // No memory leaks...
    em.close();
    emf.close();
  }
}