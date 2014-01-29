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
import org.springframework.ui.Model;
import com.vmgs.entity.Contact;
import com.vmgs.entity.Category;
import com.vmgs.service.ContactService;
import com.vmgs.dao.CategoryDao;
import javax.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/prueba")
//@SessionAttributes({"usuario","companyid","modelandviewVar"})//manejo de session spring mvc, con esto todo lo que guardamos en estas variables
//lo guarda tambien en session
public class PruebaController {

	@Autowired
	private ContactService contactService;
	
	@Autowired
	private CategoryDao categoryDao;
	
	private Contact currentContact;
	
	static{
		System.out.println("arranca la prueba");
	}

	@RequestMapping("/index/{tipoPrueba}")
	public ModelAndView PuntodeEntradaDePruebas(@PathVariable("tipoPrueba") String tipoPrueba, Model model, HttpSession session) {
		String resp="";
		Category categoria;	
		//model.addAttribute("companyid",3658851);
		//model.addAttribute("usuario","victor guerrero");
		
		session.setAttribute("user","testuser");//esta si es la session http, @SessionAttributes no es lo mismo que httpSession
		//ahora vez a prueba/hello y veraz las variables mostradas
		
		//obtener la session
		if(session.getAttribute("user") != null){
			String user = session.getAttribute("user").toString();
			System.out.println("Usuario: "+ user);
		}
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
		ModelAndView response = new ModelAndView();
		response.addObject("modelandviewVar","variable del ModelAndView");//este addObject se utiliza cuando @SessionAttributes esta ativado
		//si quieres agregar algo hazlo con addAtribute que lo agrega en requestScope
		response.addAttribute("usuario","victor guerrero");
		response.addObject("resultado", resp);
		response.setViewName("prueba");
		return response;
		//return new ModelAndView("prueba","resultado", resp);
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
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String gotoHello(Model model){
		model.addAttribute("message","spring mvc 3 session test");
		return "hello";
	}

	
}
