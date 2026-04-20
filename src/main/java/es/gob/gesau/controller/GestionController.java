package es.gob.gesau.controller;

import java.net.http.HttpRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.gob.gesau.config.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "gestion")
public class GestionController {

	private final UserService userService;

	public GestionController(UserService userService) {
		this.userService = userService;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/usuarios")
	public String usuarios(Model model) {

		model.addAttribute("usuarios", userService.getAllUsuarios());

		return "gestion/usuarios";
	}

	@RequestMapping(value = { "/" })
	public String perfil() {
		return "redirect:perfil";
	}

	@GetMapping(value = "/perfil")
	public String perfil(Model model) {

		model.addAttribute("userName", userService.getUserNameUsuarioLogeado());
		model.addAttribute("userId", userService.getUserIdUsuarioLogeado());

		return "gestion/perfil";
	}

	@GetMapping("/actualizaContrasena")
	public String actualizaContrasena(Model model) {

		model.addAttribute("userId", userService.getUserIdUsuarioLogeado());

		return "gestion/perfil :: #changePasswordForm";
	}
	@PostMapping("/actualizaContrasena")
	public String actualizaContrasena(Model model,
			@RequestParam Long userId, 
			@RequestParam String currentPassword, 
			@RequestParam String newPassword,
			@RequestParam String confirmPassword) {

		try {
			userService.changePassword(userId, currentPassword, newPassword, confirmPassword);

			model.addAttribute("success", "Contraseña actualizada correctamente");
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("openModal", true);
		}

		model.addAttribute("userId", userService.getUserIdUsuarioLogeado());

		return "gestion/perfil :: #changePasswordForm";
	}

	@PostMapping(value = "/switchEnabledUser")
	@ResponseBody
	public String switchEnabledUser(@RequestParam Long userId, @RequestParam boolean enabled) {

		try {
			userService.updateUserEnabled(userId, enabled);
			return "OK";
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}

	@PostMapping(value = "/editUsername")
	@ResponseBody
	public String editUsername(@RequestParam Long userId, @RequestParam String username) {

		try {
			userService.updateUsername(userId, username);
			return "OK";
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}

}
