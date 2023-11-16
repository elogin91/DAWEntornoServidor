package unir.familias.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import unir.familias.modelo.dao.UsuarioDao;
import unir.familias.modelo.entity.Usuario;

@Controller
public class HomeController {
	@Autowired
	private UsuarioDao udao;
	
	@GetMapping({"","/","/home"})
	public String home() {
		
		return "home";
	}
	
	@GetMapping("/login")
	public String mostrarFormLogin() {
		
		return "formLogin";
	}
	
	@PostMapping("/login")
	
	public String procLogin(RedirectAttributes ratt,HttpSession sesion, @RequestParam String username, 
								@RequestParam String password) {
		Usuario usuario = udao.login(username, password);
		if (usuario != null ) {
			sesion.setAttribute("usuario", usuario);
			return "redirect:/";
		}
		ratt.addFlashAttribute("mensaje", "usuario o password incorrecto");
		return "redirect:/login";
	}
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		
		sesion.removeAttribute("usuario");
		sesion.invalidate();
		
		return "forward:/";
	}
}
