package com.vmgs.dao;

import java.util.List;

import com.vmgs.entity.Contact;

public interface ContactDao {
	public Contact getContactById(Integer id);

	public void addContact(Contact contact);
	
	public void updateContact(Contact contact);

	public List<Contact> listContact();
	
	public List<Contact> findALL();

	public void removeContact(Integer id);
	
	public List<Contact> getContactsByCategory(int categoryId);
	
	public String queryInnerMultipleEntitiesResult();
	

}
