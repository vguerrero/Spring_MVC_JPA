package com.vmgs.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.vmgs.entity.Contact;
import javax.persistence.Query;

@Repository
public class ContactDaoImpl implements ContactDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Contact getContactById(Integer id){
		return em.find(Contact.class, id);
	}

	@Override
	public void addContact(Contact contact) {
		em.persist(contact);
	}
	
	@Override
	public void updateContact(Contact contact){
		em.merge(contact);
	}
	
	@Override
	public List<Contact> listContact() {
		return em.createQuery("SELECT p FROM Contact p").getResultList();
		
	}
	
	public List<Contact> findALL(){
		Query query = em.createNamedQuery(Contact.FIND_ALL);
		List<Contact> resultados = query.getResultList();
		return resultados;
	}

	@Override
	public void removeContact(Integer id) {
		Contact toremove = em.find(Contact.class, id);
		em.remove(toremove);
	}

}
