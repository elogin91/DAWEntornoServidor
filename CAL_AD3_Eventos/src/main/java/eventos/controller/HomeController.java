package eventos.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

/**
 * Controlador que gestiona las operaciones relacionadas con la página de inicio del sistema.
 * Extiende de {@link BaseController} para heredar funcionalidades comunes y métodos de utilidad.
 */
@Controller
public class HomeController extends BaseController {
	
	 /**
     * Muestra la página de inicio del sistema.
     *
     * @param model          el modelo utilizado para pasar datos a la vista
     * @param httpSession    la sesión HTTP
     * @param authentication la información de autenticación del usuario (puede ser nulo si no está autenticado)
     * @param tipo           el tipo de evento a filtrar (puede ser nulo o vacío)
     * @return la vista "home" con los eventos destacados y activos
     */
	@GetMapping("/")
	public String home(Model model, HttpSession httpSession, Authentication authentication, @RequestParam(required = false) String tipo) {
		setSessionAttributes(httpSession, authentication);
		model.addAttribute("eventosDestacados", filterTypes(eventoDao.buscarEventosActivosyDestacados(), tipo));
		return "home";
	}
}
