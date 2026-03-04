package es.gob.gesau.controller;

import java.net.http.HttpRequest;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.gesau.config.service.UserService;
import es.gob.gesau.model.UserEntity;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class GesauController {
	
	private final UserService userService;	
	

	public GesauController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login")	
	public String login() {		
		return "login/login";
	}
	
	@RequestMapping(value={"/","index.html"})	
	public String index() {		
		return "index";
	}
	
	@GetMapping(value="/register")
	public String register(Model model) {
		
	    model.addAttribute("user", new UserEntity());
	    model.addAttribute("error", "N");
	    model.addAttribute("creado", "N");
	    
	    return "register";
	}
	@PostMapping(value="/register")
	public String register(Model model, @ModelAttribute UserEntity user) {
		boolean error = false;
		boolean creado = false;
		
		if(user!=null) {
			UserEntity u = userService.register(user.getUsername(), user.getPassword());
			if (u!=null) {
				creado=true;
			} else {
				error=true;					
			}			
		}
		
	    model.addAttribute("user", new UserEntity());
	    model.addAttribute("error", error?"S":"N");
	    model.addAttribute("creado", creado?"S":"N");
	    
	    return "register";
	}


}
