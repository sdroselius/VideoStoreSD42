package com.skilldistillery.jpavideostore.client;

import java.util.List;

import com.skilldistillery.jpavideostore.entities.Staff;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPQLClient {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoStore");
		EntityManager em = emf.createEntityManager();
		
		int id = 19;
//		String jqpl ="SELECT s FROM Staff s WHERE s.id < 10";
		String jqpl ="SELECT s FROM Staff s WHERE s.id < :empId";
		List<Staff> emps = em.createQuery(jqpl, Staff.class)
				             .setParameter("empId", id)
				             .getResultList();
		
		for (Staff emp : emps) {
			System.out.println(emp.getFirstName() + " " + emp.getLastName());
		}
		
//		jqpl ="SELECT s.lastName FROM Staff s WHERE s.id < 10";
//		List<String> names = em.createQuery(jqpl, String.class).getResultList();
//		
//		for (String name : names) {
//			System.out.println(name);
//		}
//		
//		jqpl ="SELECT s.firstName, s.lastName FROM Staff s WHERE s.id < 10";
//		List<Object[]> fullNames = em.createQuery(jqpl, Object[].class).getResultList();
//		
//		for (Object[] fullName : fullNames) {
//			System.out.println(fullName[1] + ", " + fullName[0]);
//		}
		
		em.close();
		emf.close();

	}

}