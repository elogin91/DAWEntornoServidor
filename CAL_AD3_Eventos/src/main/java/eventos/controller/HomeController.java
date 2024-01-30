package eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import eventos.modelo.dao.EventoDaoImpl;
import eventos.modelo.dao.TipoDaoImpl;

@Controller
public class HomeController {
	@Autowired
	private EventoDaoImpl eventoDaoImpl;
	@Autowired
	private TipoDaoImpl tipoDaoImpl;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("eventosDestacados", eventoDaoImpl.buscarEventosDestacados());
		model.addAttribute("tipos", tipoDaoImpl.buscarTodosTipo());
		return "home";
	}
	
	@GetMapping("/login")
	public String entrandoUsuario() {
		return "login";
	}

}
