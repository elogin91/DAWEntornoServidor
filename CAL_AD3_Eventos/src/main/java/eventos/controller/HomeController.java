package eventos.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController extends BaseController {
	@GetMapping("/")
	public String home(Model model, HttpSession httpSession, Authentication authentication, @RequestParam(required = false) String tipo) {
		setSessionAttributes(httpSession, authentication);
		model.addAttribute("eventosDestacados", filterTypes(eventoDao.buscarEventosActivosyDestacados(), tipo));
		return "home";
	}
}
