package com.skilldistillery.jpavideostore.client;

import java.util.List;

import com.skilldistillery.jpavideostore.entities.Address;
import com.skilldistillery.jpavideostore.entities.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CRUDClient {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoStore");
	private EntityManager em = emf.createEntityManager();

	public static void main(String[] args) {
		CRUDClient client = new CRUDClient();
//		client.updateNullEmails();
//		client.addNewAddress();
		client.deleteAddress(701);
	}

	public void updateNullEmails() {
		String jpql = "SELECT c FROM Customer c WHERE c.email = '' OR c.email IS NULL";
		List<Customer> noEmails = em.createQuery(jpql, Customer.class).getResultList();
		em.getTransaction().begin();
		for (Customer customer : noEmails) {
			String email = customer.getFirstName() + "." + customer.getLastName();
			email += "@sdcustomer.org";
			customer.setEmail(email);
		}
//		    em.flush();
		em.getTransaction().commit();
	}

	public void addNewAddress() {
		Address addr = new Address();
		addr.setCity("Denver");
		addr.setStreet("123 Fake St.");
		addr.setState("Colorado");
		addr.setPostalCode("80209");
		addr.setPhone("3035551234");
		em.getTransaction().begin();
		em.persist(addr);
		em.getTransaction().commit();
	}

	public void deleteAddress(int id) {
		Address toDelete = em.find(Address.class, id);
		if (em != null) {
			em.getTransaction().begin();
			em.remove(toDelete);
			em.getTransaction().commit();
		}
	}

}