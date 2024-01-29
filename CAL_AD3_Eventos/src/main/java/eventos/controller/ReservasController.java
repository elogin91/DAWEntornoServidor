package eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.ReservaDaoImpl;
import eventos.modelo.dao.UsuarioDao;
import eventos.modelo.entitis.Reserva;


@Controller
public class ReservasController {

	@Autowired
	private ReservaDaoImpl reservaDaoImpl;
	
	@Autowired
	private EventoDao eventoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@GetMapping("/reservas/misReservas")
	public String verMisReservas(Model model,Authentication authentication) {
		model.addAttribute("misReservas", reservaDaoImpl.buscarReservasPorCliente(authentication.getName()));
		return "/misReservas";
	}
	
	@PostMapping("/reservas/evento{idEvento}/alta")
	public String altaReserva(Reserva reserva, RedirectAttributes ratt, Authentication authentication, @PathVariable("idEvento")int idEvento) {
		reserva.setEvento(eventoDao.buscarUnEvento(idEvento));
		reserva.setUsuario(usuarioDao.buscarUnUsuario(authentication.getName()));
		
		if (reservaDaoImpl.altaReserva(reserva) != null) {
			ratt.addFlashAttribute("mensaje", "Evento dado de alta");
		} else {
			ratt.addFlashAttribute("mensaje", "Algo ha fallado, alta no realizada");
		}
		return "redirect:/";

	}
	
	@GetMapping("/reservas/eliminar/{id}")
	public String eliminarReserva(@PathVariable("id") int idReserva) {
		reservaDaoImpl.cancelarReserva(reservaDaoImpl.buscarUnaReserva(idReserva));
		return "forward:/reservas/misReservas";
	}
	
	@PostMapping("/reservas/evento{idEvento}/modificar/{id}")
	public String modificarReserva(@PathVariable("id") int idReserva, Reserva reservaNueva,@PathVariable("idEvento") int idEvento, RedirectAttributes ratt) {
		Reserva reservaAntigua = reservaDaoImpl.buscarUnaReserva(idReserva);
		
		if (reservaDaoImpl.modificarReserva(reservaAntigua, reservaNueva.getCantidad()) != null) {
			ratt.addFlashAttribute("mensaje", "Evento dado de alta");
		} else {
			ratt.addFlashAttribute("mensaje", "Algo ha fallado, alta no realizada");
		}
		return "redirect:/";
	}

}
