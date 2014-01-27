package com.vmgs.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.vmgs.entity.*;//every entities
import javax.persistence.Query;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.Tuple;

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
	
	@Override
	public List<Contact> getContactsByCategory(int categoryId){
		/*String s = "SELECT c FROM Contact c JOIN c.category cat where cat.id = :categoryId"; //jpa query
		Query q = em.createQuery(s);
		q.setParameter("categoryId", categoryId);
		return q.getResultList();*/ 
		
		JPAQuery query = new JPAQuery(em);
		QContact contact = QContact.contact;
		QCategory category = QCategory.category;
		List<Contact> contacts = query.from(contact, category)
			.innerJoin(contact.category, category)
			.where(category.id.eq(categoryId)).list(contact);
		
		return contacts;
	}
	
	public String queryInnerMultipleEntitiesResult(){
		String strresult="";
		JPAQuery query = new JPAQuery(em);
		QContact contact = QContact.contact;
		QCategory category = QCategory.category;
		List<Tuple> result = query.from(contact, category)	
					.innerJoin(contact.category, category)		
					.list(contact.firstname, contact.lastname, category.name );
		strresult += "firstname "+" , " +" lastname "+ " CategoryName </br></br></br>";
		for (Tuple row : result) {
			strresult += row.get(contact.firstname)+" , " + row.get(contact.lastname)+ " , "+ row.get(category.name) + "</br>";
		}
		return strresult;
	}

}
