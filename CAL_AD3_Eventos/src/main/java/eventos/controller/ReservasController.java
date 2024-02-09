package eventos.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.entitis.Reserva;
import jakarta.servlet.http.HttpSession;

/**
 * Controlador que gestiona las operaciones relacionadas con las reservas en el sistema.
 * Extiende de {@link BaseController} para heredar funcionalidades comunes y métodos de utilidad.
 */
@Controller
public class ReservasController extends BaseController {

	/**
     * Muestra las reservas del cliente actual.
     *
     * @param model          el modelo utilizado para pasar datos a la vista
     * @param httpSession    la sesión HTTP
     * @param authentication la información de autenticación del usuario
     * @return la vista "misReservas" con las reservas pendientes y caducadas del cliente
     */
	@GetMapping("/reservas/misReservas")
	public String verMisReservas(Model model,HttpSession httpSession, Authentication authentication) {
		setSessionAttributes(httpSession, authentication);
		model.addAttribute("misReservasPendientes", reservaDao.buscarReservasPorClientePendientes(authentication.getName()));
		model.addAttribute("misReservasCaducadas", reservaDao.buscarReservasPorClienteCaducadas(authentication.getName()));
		return "/misReservas";
	}
	
	/**
    * Procesa el formulario para dar de alta una nueva reserva.
    *
    * @param reserva        la reserva a dar de alta
    * @param ratt           atributos para pasar mensajes flash
    * @param authentication la información de autenticación del usuario
    * @param idEvento       el ID del evento asociado a la reserva
    * @return redirecciona a la página de inicio con un mensaje de éxito o fracaso
    */
	@PostMapping("/reservas/evento{idEvento}/alta")
	public String altaReserva(Reserva reserva, RedirectAttributes ratt, Authentication authentication, @PathVariable("idEvento")int idEvento) {
		reserva.setEvento(eventoDao.buscarUnEvento(idEvento));
		reserva.setUsuario(usuarioDao.buscarUnUsuario(authentication.getName()));
		
		if (reservaDao.altaReserva(reserva) != null) {
			ratt.addFlashAttribute("mensaje", "Reserva dada de alta");
		} else {
			ratt.addFlashAttribute("mensaje", "Algo ha fallado, alta no realizada");
		}
		return "redirect:/";

	}
	
	  /**
     * Elimina una reserva existente.
     *
     * @param idReserva      el ID de la reserva a eliminar
     * @param httpSession    la sesión HTTP
     * @param authentication la información de autenticación del usuario
     * @return redirecciona a la página de reservas del cliente
     */
	@GetMapping("/reservas/eliminar/{id}")
	public String eliminarReserva(@PathVariable("id") int idReserva,HttpSession httpSession, Authentication authentication) {
		setSessionAttributes(httpSession, authentication);
		reservaDao.cancelarReserva(reservaDao.buscarUnaReserva(idReserva));
		return "forward:/reservas/misReservas";
	}
	
	
	 /**
     * Modifica una reserva existente.
     *
     * @param idReserva      el ID de la reserva a modificar
     * @param reservaNueva   la nueva reserva
     * @param idEvento       el ID del evento asociado a la reserva
     * @param ratt           atributos para pasar mensajes flash
     * @return redirecciona a la página de inicio con un mensaje de éxito o fracaso
     */
	@PostMapping("/reservas/evento{idEvento}/modificar/{id}")
	public String modificarReserva(@PathVariable("id") int idReserva, Reserva reservaNueva,@PathVariable("idEvento") int idEvento, RedirectAttributes ratt) {
		Reserva reservaAntigua = reservaDao.buscarUnaReserva(idReserva);
		
		if (reservaDao.modificarReserva(reservaAntigua, reservaNueva.getCantidad()) != null) {
			ratt.addFlashAttribute("mensaje", "Evento dado de alta");
		} else {
			ratt.addFlashAttribute("mensaje", "Algo ha fallado, alta no realizada");
		}
		return "redirect:/";
	}

}
