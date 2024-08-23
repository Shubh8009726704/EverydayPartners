package org.everyday.controller;

import java.security.Principal;

import org.everyday.model.UserDtls;
import org.everyday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cities")
public class CitiesController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public void getUserDetails(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			UserDtls userDtls = userService.getUserByEmail(email);
			m.addAttribute("user", userDtls);
		}
	}
	
      
	    //cities  controller
	
		@GetMapping("/barberCity")
	    public String barber_cities() 
	    {
	    	return "cities/barber_cities";
	    }
		
		@GetMapping("/cleanerCity")
	    public String cleaner_cities() 
	    {
	    	return "cities/cleaner_cities";
	    }
		
		@GetMapping("/electricianCity")
	    public String electrician_cities() 
	    {
	    	return "cities/electrician_cities";
	    }
		
		@GetMapping("/painterCity")
	    public String painter_cities() 
	    {
	    	return "cities/painter_cities";
	    }
		
		@GetMapping("/plumberCity")
	    public String plumber_cities() 
	    {
	    	return "cities/plumber_cities";
	    }
		
		@GetMapping("/chefCity")
	    public String sheff_cities() 
	    {
	    	return "cities/sheff_cities";
	    }
}
