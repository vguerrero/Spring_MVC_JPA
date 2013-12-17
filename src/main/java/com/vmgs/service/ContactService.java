package com.vmgs.service;

import java.util.List;

import com.vmgs.entity.Contact;

public interface ContactService {
	public void addContact(Contact contact);

	public List<Contact> listContact();

	public void removeContact(Integer id);
}
