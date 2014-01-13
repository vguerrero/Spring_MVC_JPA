package com.vmgs.service;

import java.util.List;

import com.vmgs.entity.Contact;

public interface ContactService {
	
	public Contact getContactById(Integer id);

	public void addContact(Contact contact);
	
	public void updateContact(Contact contact);

	public List<Contact> listContact();
	
	public List<Contact> findALL();

	public void removeContact(Integer id);
}
