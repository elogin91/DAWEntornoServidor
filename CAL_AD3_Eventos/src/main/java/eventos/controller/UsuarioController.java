package eventos.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.entitis.Usuario;

@Controller
public class UsuarioController extends BaseController {

	@GetMapping("/login")
	public String entrandoUsuario() {
		return "login";
	}

	@GetMapping("/register")
	public String registrarNuevoUsuario() {
		return "formRegister";
	}

	@PostMapping("/register")
	public String guardandoNuevoUsuario(Model model, Usuario usuario, RedirectAttributes redirectAttributes) {
		usuario.setEnabled(1);
		usuario.setFechaRegistro(new Date());
		usuario.addPerfil(perfilDao.buscarUnoPerfil(1));
		usuario.setPassword("{noop}"+ usuario.getPassword());
		if (usuarioDao.altaUsuario(usuario)!=null) {
			redirectAttributes.addFlashAttribute("mensaje", "alta realizada");
			return "redirect:/login";
		} else {
			model.addAttribute("mensaje", "ya existe como usuario");
			return "/";
		}
	}
}
