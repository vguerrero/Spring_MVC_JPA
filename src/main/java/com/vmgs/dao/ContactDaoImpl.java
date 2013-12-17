package com.vmgs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vmgs.entity.Contact;

@Repository
public class ContactDaoImpl implements ContactDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addContact(Contact contact) {
		em.persist(contact);
	}

	@Override
	public List<Contact> listContact() {
		return em.createQuery("SELECT p FROM Contact p").getResultList();
		
	}

	@Override
	public void removeContact(Integer id) {
		Contact toremove = em.find(Contact.class, id);
		em.remove(toremove);
	}

}
