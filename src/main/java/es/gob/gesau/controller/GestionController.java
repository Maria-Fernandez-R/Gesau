package es.gob.gesau.controller;

import java.security.AuthProvider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.gesau.config.service.UserService;
import es.gob.gesau.model.UserEntity;

@Controller
@RequestMapping(value = "gestion")
public class GestionController {
	
	private final UserService userService;	
	

	public GestionController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/usuarios")	
	public String login() {		
		return "login/login";
	}
	
	@RequestMapping(value={"/"})	
	public String perfil() {		
		return "redirect:perfil";
	}
	
	@GetMapping(value="/perfil")
	public String perfil(Model model) {
		
	    model.addAttribute("userName", userService.getUserNameUsuarioLogeado());
	    
	    return "gestion/perfil";
	}

	@PostMapping(value="/actualizaContrasena")
	public String perfil(Model model, @RequestParam String currentPassword, @RequestParam String newPassword, @RequestParam String confirmPassword) {
		
		try {	
			userService.changePassword(currentPassword, newPassword, confirmPassword);
	
			model.addAttribute("success", "Contraseña actualizada correctamente");
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("openModal", true);
		}
	    
	    return "gestion/perfil :: #changePasswordForm";
	}




}
