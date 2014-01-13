package com.vmgs.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.vmgs.entity.Category;
import javax.persistence.Query;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Category getCategoryById(Integer id){
		return em.find(Category.class, id);
	}

	@Override
	@Transactional
	public void addCategory(Category category) {
		em.persist(category);
	}
	
	
	@Override
	public List<Category> listCategory() {
		return em.createQuery("SELECT c FROM Category c").getResultList();
		
	}

	@Override
	public void removeCategory(Integer id) {
		Category toremove = em.find(Category.class, id);
		em.remove(toremove);
	}
	
	@Override
	public Category getCategoryByName(String name){
		String jpql= "SELECT c FROM Category c where c.name = :name";
		Query q = em.createQuery(jpql);
		q.setParameter("name", name);
		return (Category)q.getSingleResult();//no puede haber mas de una categoria con el mismo nombre
	}
}
