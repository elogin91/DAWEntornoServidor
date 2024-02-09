package eventos.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;
import jakarta.servlet.http.HttpSession;

/**
 * Controlador que gestiona las operaciones relacionadas con los eventos en el sistema.
 * Extiende de {@link BaseController} para heredar funcionalidades comunes y métodos de utilidad.
 */
@Controller
public class EventosController extends BaseController{

	 /**
     * Muestra los detalles de un evento específico.
     *
     * @param idEvento       el ID del evento a visualizar
     * @param model          el modelo utilizado para pasar datos a la vista
     * @param httpSession    la sesión HTTP
     * @param authentication la información de autenticación del usuario (puede ser nulo si no está autenticado)
     * @param tipo           el tipo de evento a filtrar (puede ser nulo o vacío)
     * @return la vista "detalleEvento" si se encuentra el evento, de lo contrario, redirige a la página de inicio
     */
	@GetMapping("/eventos/verUno/{id}")
	public String verUnEvento(@PathVariable("id") int idEvento, Model model, HttpSession httpSession, Authentication authentication, @RequestParam(required = false) String tipo) {
		setSessionAttributes(httpSession, authentication);

		Evento evento = eventoDao.buscarUnEvento(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			if(authentication!= null) {
				List<Reserva> reservasDelUsuario = reservaDao.buscarReservasPorClienteYEvento(authentication.getName(), idEvento);
				model.addAttribute("cantidadReservas", reservasDelUsuario.stream().mapToInt(it -> it.getCantidad()).sum());
				model.addAttribute("reserva", !reservasDelUsuario.isEmpty() ? reservasDelUsuario.get(0) : null);
			}
			List<Reserva> reservasDelEvento = reservaDao.buscarReservasPorEvento(idEvento);
			model.addAttribute("aforoDisponible", evento.getAforoMaximo() - reservasDelEvento.stream().mapToInt(it -> it.getCantidad()).sum());
			
			return "detalleEvento";
		} else {
			model.addAttribute("mensaje", "El evento buscado no existe");
			return "forward:/";
		}
	}
	
	  /**
     * Muestra una lista de eventos destacados.
     *
     * @param model          el modelo utilizado para pasar datos a la vista
     * @param httpSession    la sesión HTTP
     * @param authentication la información de autenticación del usuario (puede ser nulo si no está autenticado)
     * @param tipo           el tipo de evento a filtrar (puede ser nulo o vacío)
     * @return la vista "eventos" con la lista de eventos destacados
     */
	@GetMapping("/eventos/destacados")
	public String mostrarEventosDestacados(Model model,  HttpSession httpSession, Authentication authentication, @RequestParam(required = false) String tipo) {
		setSessionAttributes(httpSession, authentication);
		model.addAttribute("eventos", filterTypes(eventoDao.buscarEventosDestacados(), tipo));
		model.addAttribute("mensaje", "Lista de eventos destacados.");
		return "eventos";
	}
	
	/**
     * Muestra una lista de eventos activos.
     *
     * @param model          el modelo utilizado para pasar datos a la vista
     * @param httpSession    la sesión HTTP
     * @param authentication la información de autenticación del usuario (puede ser nulo si no está autenticado)
     * @param tipo           el tipo de evento a filtrar (puede ser nulo o vacío)
     * @return la vista "eventos" con la lista de eventos activos
     */
	@GetMapping("/eventos/activos")
	public String mostrarEventosActivos(Model model,  HttpSession httpSession, Authentication authentication, @RequestParam(required = false) String tipo) {
		setSessionAttributes(httpSession, authentication);
		model.addAttribute("eventos", filterTypes(eventoDao.buscarEventosActivos(), tipo));
		model.addAttribute("mensaje", "Lista de eventos activos.");
		return "eventos";
	}
	
	/**
     * Muestra el formulario para dar de alta un nuevo evento.
     *
     * @param model          el modelo utilizado para pasar datos a la vista
     * @param httpSession    la sesión HTTP
     * @param authentication la información de autenticación del usuario (puede ser nulo si no está autenticado)
     * @param tipo           el tipo de evento a filtrar (puede ser nulo o vacío)
     * @return la vista "formularioAltaEvento" con el formulario para dar de alta un nuevo evento
     */
	@GetMapping("/eventos/alta")
	public String mostrarFormularioAlta(Model model,  HttpSession httpSession, Authentication authentication, @RequestParam(required = false) String tipo) {
		setSessionAttributes(httpSession, authentication);
		model.addAttribute("evento", new Evento());
		model.addAttribute("tipos", tipoDao.buscarTodosTipo());
		return "formularioAltaEvento";
	}
	
	/**
     * Procesa el formulario para dar de alta un nuevo evento.
     *
     * @param evento  el evento a dar de alta
     * @param ratt    atributos para pasar mensajes flash
     * @return redirecciona a la página de inicio con un mensaje de éxito o fracaso
     */
	@PostMapping("/eventos/alta")
	public String procesarFormularioAlta(Evento evento, RedirectAttributes ratt) {
		evento.setTipo(tipoDao.buscarUnTipo(evento.getTipo()));
		if (eventoDao.crearEvento(evento) != null) {
			ratt.addFlashAttribute("mensaje", "Evento dado de alta");
		} else {
			ratt.addFlashAttribute("mensaje", "Algo ha fallado, alta no realizada");
		}
		return "redirect:/";

	}

}
