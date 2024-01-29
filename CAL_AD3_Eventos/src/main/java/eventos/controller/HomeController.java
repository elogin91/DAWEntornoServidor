package eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import eventos.modelo.dao.EventoDaoImpl;

@Controller
public class HomeController {
	@Autowired
	private EventoDaoImpl eventoDaoImpl;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("eventosDestacados", eventoDaoImpl.buscarEventosDestacados());
		return "home";
	}
	
	@PostMapping("/login")
	public String entrandoUsuario() {
		return "redirect:/";
	}

}
