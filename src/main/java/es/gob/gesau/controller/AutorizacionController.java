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
import org.springframework.web.multipart.MultipartFile;

import es.gob.gesau.config.service.UserService;
import es.gob.gesau.model.UserEntity;
import es.gob.gesau.service.AutorizacionesService;
import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("autorizaciones")
public class AutorizacionController {
	
	private final UserService userService;	
	private final AutorizacionesService autorizacionesService;

	public AutorizacionController(UserService userService, AutorizacionesService autorizacionesService) {
		this.userService = userService;
		this.autorizacionesService = autorizacionesService;
	}

	@GetMapping(value={"","/"})	
	public String autorizaciones(Model model) {		
		
		model.addAttribute("autorizaciones", autorizacionesService.getAllAutorizaciones());
		
		return "autorizaciones/autorizaciones";
	}
	
	@GetMapping("carga")	
	public String carga() {		
		return "autorizaciones/carga";
	}
	
	@PostMapping(value="carga", consumes = {"multipart/form-data"})	
	public String cargaFichero(@RequestParam("ficheroCodificacion") MultipartFile[] ficheroCodificacion,
			Model model) {		
		
		model.addAttribute("errores", autorizacionesService.cargaFicheroCodificacion(ficheroCodificacion));
		
		return "autorizaciones/carga";
	}
	
	@PostMapping(value="borrarAutorizaciones")
	public String borrarAutorizaciones() {
		
		autorizacionesService.borrarAutorizaciones();
		
		return "redirect:";
	}
	
	


}
