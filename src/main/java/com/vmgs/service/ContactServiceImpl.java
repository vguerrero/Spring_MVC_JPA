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
	
	@Override
	public Contact getContactById(Integer id){
		return contactDAO.getContactById(id);
	}

	@Transactional
	public void addContact(Contact contact) {
		contactDAO.addContact(contact);

	}
	
	@Transactional
	public void updateContact(Contact contact) {
		contactDAO.updateContact(contact);
	}

	@Override
	public List<Contact> listContact() {
		return contactDAO.listContact();
	}
	
	@Override
	public List<Contact> findALL(){
		return contactDAO.findALL();
	}

	@Transactional
	public void removeContact(Integer id) {
		contactDAO.removeContact(id);

	}

}
