package com.vmgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmgs.entity.Contact;
import com.vmgs.dao.ContactDao;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDAO;

	@Transactional
	public void addContact(Contact contact) {
		contactDAO.addContact(contact);

	}

	@Override
	public List<Contact> listContact() {
		return contactDAO.listContact();
	}

	@Transactional
	public void removeContact(Integer id) {
		contactDAO.removeContact(id);

	}

}
