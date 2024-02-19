package eventos.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.entitis.Usuario;

/**
 * Controlador que gestiona las operaciones relacionadas con los usuarios en el sistema.
 * Extiende de {@link BaseController} para heredar funcionalidades comunes y métodos de utilidad.
 */
@Controller
public class UsuarioController extends BaseController {

	/**
     * Muestra la página de inicio de sesión.
     *
     * @return la vista "login" para iniciar sesión
     */
	@GetMapping("/login")
	public String entrandoUsuario() {
		return "login";
	}

	 /**
     * Muestra el formulario para registrar un nuevo usuario.
     *
     * @return la vista "formRegister" para registrar un nuevo usuario
     */
	@GetMapping("/register")
	public String registrarNuevoUsuario() {
		return "formRegister";
	}

	/**
     * Guarda un nuevo usuario en el sistema.
     *
     * @param model              el modelo utilizado para pasar datos a la vista
     * @param usuario            el nuevo usuario a registrar
     * @param redirectAttributes atributos para pasar mensajes flash
     * @return redirecciona a la página de inicio de sesión con un mensaje de éxito o fracaso
     */
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
