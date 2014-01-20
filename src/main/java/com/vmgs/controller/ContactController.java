package com.vmgs.controller;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmgs.entity.Contact;
import com.vmgs.entity.Category;
import com.vmgs.service.ContactService;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import com.vmgs.dao.CategoryDao;

@Controller
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private CategoryDao categoryDao;
	
	private List<Contact> contactList;
	
	/*@ModelAttribute es en Spring MVC la forma de enlazar el modelo a la vista */
	@ModelAttribute("contactList") 
	public List<Contact> getcontactList(){
		contactList = contactService.findALL();
		return contactList ;//contactService.listContact();
	}
	
	
	@RequestMapping("/index")
	public String listContacts(Map<String, Object> map) {
		map.put("contact", new Contact());
		map.put("categoryList", categoryDao.listCategory());
		return "contact";
	}
	
	//here @ModelAttribute("contact") es opcional funciona si lo quitamos tambien
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addContact(@Valid @ModelAttribute("contact") Contact contact,  BindingResult result) {
		System.out.println( "addContact method!" );
		if(result.hasErrors()) {
			System.out.println( "Error de Validacion!" );
			return "contact";
		}
		Integer categoryId = contact.getCategory().getId();
		Category cat = null;
		if(categoryId > 0){
			cat = categoryDao.getCategoryById(categoryId);
		}
		contact.setCategory(cat);
		//System.out.println("cat id: "+ contact.getCategory().getId() +" - name: " + contact.getCategory().getName() );
		System.out.println(contact.getId());
		if(contact.getId() == null){//es un contacto nuevo
			contactService.addContact(contact);
		}
		else{//actualizar el contacto
			contactService.updateContact(contact);
		}
		return "redirect:/contact/index";
	}
	
	@RequestMapping("/add")
	public String gotoIndex(){
		return "redirect:/contact/index";
	}

	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId") Integer contactId) {
		contactService.removeContact(contactId);
		return "redirect:/contact/index";
	}
	
	@RequestMapping("/update/{contactId}")
	public String updateContact(@PathVariable("contactId") Integer contactId, Map<String, Object> map) {
		if(contactId > 0){
			Contact c = contactService.getContactById(contactId);
			Category cat = c.getCategory();
			map.put("contact", c);
			map.put("categoryList", categoryDao.listCategory());
		}
		return "contact";
	}
}
