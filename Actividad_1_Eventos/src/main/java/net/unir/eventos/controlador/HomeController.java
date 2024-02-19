package net.unir.eventos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.unir.eventos.modelo.dao.EventoDao;

/**
 * Controlador encargado de gestionar la página principal (home).
 */
@Controller
public class HomeController {

	/**
	 * Instancia de EventoDao para acceder a la persistencia de eventos.
	 */
	@Autowired
	private EventoDao edao;

	/**
	 * Método para mostrar la página principal.
	 * 
	 * @param model Modelo de datos para agregar la lista de eventos activos y no
	 *              activos.
	 * @return Retorna la vista para la página principal.
	 */
	@GetMapping("/")
	public String mostrarHome(Model model) {
		// Agrega la lista de eventos activos y no activos al modelo
		model.addAttribute("eventosActivos", edao.findActives());
		model.addAttribute("eventosNoActivos", edao.findNotActives());

		return "index";
	}
}
