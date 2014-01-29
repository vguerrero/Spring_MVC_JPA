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
@RequestMapping("/homedir")
//@SessionAttributes({"usuario","companyid","modelandviewVar"})//manejo de session spring mvc
public class homedirController {

		
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String gotoHello(Model model){
		model.addAttribute("message","spring mvc 3 session test from homedir");
		return "hello";
	}

	
}
