package com.vmgs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmgs.entity.Contact;
import com.vmgs.service.ContactService;
import javax.validation.Valid;

@Controller
@RequestMapping("/contact")
public class ContactController {

	@Autowired
	private ContactService contactService;

	@RequestMapping("/index")
	public String listContacts(Map<String, Object> map) {

		map.put("contact", new Contact());
		map.put("contactList", contactService.listContact());

		return "contact";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@Valid Contact contact,  BindingResult result) {
		System.out.println( "addContact method!" );
		if(result.hasErrors()) {
			System.out.println( "Error de Validacion!" );
			return "contact";
		}

		contactService.addContact(contact);

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
}
