package eventos.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
	
	@GetMapping("/login")
	public String entrandoUsuario() {
		return "login";
	}
	
	@GetMapping("/register")
	public String registrarNuevoUsuario() {
		return "formRegister";
	}
	
	@PostMapping("/register")
	public String guardandoNuevoUsuario() {
		return null;
	}
}
