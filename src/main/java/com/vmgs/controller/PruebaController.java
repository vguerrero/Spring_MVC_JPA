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
import org.springframework.web.servlet.ModelAndView;

import com.vmgs.entity.Contact;
import com.vmgs.entity.Category;
import com.vmgs.service.ContactService;
import com.vmgs.dao.CategoryDao;
import javax.validation.Valid;

@Controller
@RequestMapping("/prueba")
public class PruebaController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private CategoryDao categoryDao;
	
	private Contact currentContact;
	
	

	@RequestMapping("/index/{tipoPrueba}")
	public ModelAndView PuntodeEntradaDePruebas(@PathVariable("tipoPrueba") String tipoPrueba) {
		String resp="";
		Category categoria;	
		switch(tipoPrueba){
			case "test1":
				if(categoryDao != null){
					//ahora le creamos una categoria si no existe 
					categoria = categoryDao.getCategoryByName("Familia");
					if( categoria == null){
						categoria = new Category(0, "Amigos");
						
						categoryDao.addCategory(categoria);
						resp="Se agrego la categoria: "+ categoria.getName();
					}
					else{
						resp=resp + " La categoria familia ya existe";
					}
					//obtenemos el contacto que queramos por medio de su id
					currentContact = contactService.getContactById(2);
					if(currentContact != null){
						currentContact.setCategory(categoria);
						contactService.updateContact(currentContact);
						resp=resp + " ,se ha actualizado el contacto "+ currentContact.getFirstname()+ " con la categoria: " + categoria.getName();
					}
					
				}else{
				System.out.println("la categoriaDao no se creo");
				}
			break;
			case "test2":
				categoria = categoryDao.getCategoryByName("Familia");//implementacion de queryDSL
				if(categoria != null){
					resp="se encontro la categoria: ";
					resp = resp + categoria.toString();
				}
				else{ resp="No se encontro";}
				
				break;
			case "QueryDSLJoin":
				List<Contact> contacts = contactService.getContactsByCategory(2);
				for (Contact c: contacts){
					resp += c.toString() + " -- ";
				}
			break;
			
			case "ReturnMultipleColumns":
				resp = contactService.queryInnerMultipleEntitiesResult();
			break;
			
			default:
				System.out.println("se fue por default");
				break;
		}
		return new ModelAndView("prueba","resultado", resp);
	}
	
	//here @ModelAttribute("contact") es opcional funciona si lo quitamos tambien
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(@Valid @ModelAttribute("contact") Contact contact,  BindingResult result) {
		System.out.println( "addContact method!" );
		if(result.hasErrors()) {
			System.out.println( "Error de Validacion!" );
			return "contact";
		}

		contactService.addContact(contact);

		return "redirect:/contact/index";
	}
	
	

	
}
