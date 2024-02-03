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


@Controller
public class ReservasController extends BaseController {

	@GetMapping("/reservas/misReservas")
	public String verMisReservas(Model model,HttpSession httpSession, Authentication authentication) {
		setSessionAttributes(httpSession, authentication);
		model.addAttribute("misReservasPendientes", reservaDao.buscarReservasPorClientePendientes(authentication.getName()));
		model.addAttribute("misReservasCaducadas", reservaDao.buscarReservasPorClienteCaducadas(authentication.getName()));
		return "/misReservas";
	}
	
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
	
	@GetMapping("/reservas/eliminar/{id}")
	public String eliminarReserva(@PathVariable("id") int idReserva,HttpSession httpSession, Authentication authentication) {
		setSessionAttributes(httpSession, authentication);
		reservaDao.cancelarReserva(reservaDao.buscarUnaReserva(idReserva));
		return "forward:/reservas/misReservas";
	}
	
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
