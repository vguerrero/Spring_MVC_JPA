package com.vmgs.dao;

import java.util.List;

import com.vmgs.entity.Contact;

public interface ContactDao {
	public void addContact(Contact contact);

	public List<Contact> listContact();

	public void removeContact(Integer id);
}
