package net.unir.eventos.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.unir.eventos.modelo.dao.EventoDao;
import net.unir.eventos.modelo.dao.TipoDao;
import net.unir.eventos.modelo.javabean.Evento;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los
 * eventos.
 */
@RequestMapping("eventos")
@Controller
public class EventosController {

	/**
	 * Instancia de EventoDao para acceder a la persistencia de eventos.
	 */
	@Autowired
	private EventoDao edao;

	/**
	 * Instancia de TipoDao para acceder a la persistencia de tipos de eventos.
	 */
	@Autowired
	private TipoDao tdao;

	/**
	 * Método para eliminar un evento a partir de su ID.
	 *
	 * @param idEvento ID del evento a eliminar.
	 * @param model    Modelo de datos para agregar mensajes.
	 * @return Redirecciona a la página principal.
	 */
	@GetMapping("/eliminar/{id}")
	public String eliminarEvento(@PathVariable("id") int idEvento, Model model) {
		if (edao.delete(idEvento) == 1)
			model.addAttribute("mensaje", "Evento eliminado correctamente");
		else
			model.addAttribute("mensaje", "Evento no eliminado");
		return "forward:/";
	}

	/**
	 * Método para ver los detalles de un evento.
	 *
	 * @param idEvento ID del evento a ver.
	 * @param model    Modelo de datos para agregar el evento o mensajes.
	 * @return Retorna la vista para ver los detalles del evento o redirecciona a la
	 *         página principal si no existe.
	 */
	@GetMapping("/detalle/{id}")
	public String verEvento(@PathVariable("id") int idEvento, Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			return "verDetalle";
		} else {
			model.addAttribute("mensaje", "El evento no existe");
			return "forward:/";
		}
	}

	/**
	 * Método para mostrar el formulario de alta de un evento.
	 *
	 * @param model Modelo de datos para agregar un nuevo evento y la lista de tipos
	 *              de eventos.
	 * @return Retorna la vista para el formulario de alta.
	 */
	@GetMapping("/alta")
	public String mostrarFormularioAlta(Model model) {

		model.addAttribute("evento", new Evento());
		model.addAttribute("tipos", tdao.findAll());
		return "formularioAlta";
	}

	/**
	 * Método para procesar el formulario de alta de un evento.
	 *
	 * @param evento Objeto Evento a dar de alta.
	 * @param ratt   Atributos para redireccionar con mensajes.
	 * @return Redirecciona a la página principal.
	 */
	@PostMapping("/alta")
	public String procesarFormularioAlta(Evento evento, RedirectAttributes ratt) {
		evento.setTipo(tdao.findById(evento.getTipo().getIdTipo()));
		if (edao.insert(evento) == 1) {
			ratt.addFlashAttribute("mensaje", "Evento dado de alta");
		} else {
			ratt.addFlashAttribute("mensaje", "Algo ha fallado, alta no realizada");
		}
		return "redirect:/";

	}

	/**
	 * Método para mostrar el formulario de edición de un evento.
	 *
	 * @param idEvento ID del evento a editar.
	 * @param model    Modelo de datos para agregar el evento y la lista de tipos de
	 *                 eventos.
	 * @return Retorna la vista para el formulario de edición o redirecciona a la
	 *         página principal si no existe.
	 */
	@GetMapping("/editar/{id}")
	public String editarEvento(@PathVariable("id") int idEvento, Model model) {
		Evento evento = edao.findById(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			model.addAttribute("tipos", tdao.findAll());

			return "formularioEdicion";
		} else {
			model.addAttribute("mensaje", "El evento no existe");
			return "forward:/";
		}
	}

	/**
	 * Método para procesar la edición de un evento.
	 *
	 * @param evento   Objeto Evento con los nuevos valores.
	 * @param idEvento ID del evento a editar.
	 * @param ratt     Atributos para redireccionar con mensajes.
	 * @return Redirecciona a la página principal.
	 */
	@PostMapping("/editar/{id}")
	public String procesarEditarEvento(Evento evento, @PathVariable("id") int idEvento, RedirectAttributes ratt) {
		evento.setIdEvento(idEvento);
		evento.setTipo(tdao.findById(evento.getTipo().getIdTipo()));

		if (edao.updateOne(evento) == 1) {
			ratt.addFlashAttribute("mensaje", "Evento modificado");
		} else {
			ratt.addFlashAttribute("mensaje", "Evento no modificado");
		}
		return "redirect:/";
	}

	/**
	 * Método para cancelar un evento.
	 *
	 * @param idEvento ID del evento a cancelar.
	 * @param model    Modelo de datos para agregar mensajes.
	 * @return Redirecciona a la página principal.
	 */
	@GetMapping("/cancelar/{id}")
	public String procesarCancelarEvento(@PathVariable("id") int idEvento, Model model) {
		if (edao.cancelEvent(idEvento) == 1)
			model.addAttribute("mensaje", "Evento cancelado correctamente");
		else
			model.addAttribute("mensaje", "Evento no cancelado");
		return "forward:/";
	}

	/**
	 * Método de inicialización del controlador. Registra un editor personalizado
	 * para fechas.
	 *
	 * @param binder WebDataBinder para registrar el editor de fechas.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
