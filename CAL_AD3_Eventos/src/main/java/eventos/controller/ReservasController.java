package eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import eventos.modelo.dao.ReservaDaoImpl;


@Controller
public class ReservasController {

	@Autowired
	private ReservaDaoImpl reservaDaoImpl;
	
	@GetMapping("/reservas/misReservas")
	public String verMisReservas(Model model,Authentication authentication) {
		model.addAttribute("misReservas", reservaDaoImpl.buscarReservasPorCliente(authentication.getName()));
		return "/misReservas";
	}
	
	@PostMapping("/reservas/alta")
	public String altaYModificarReserva() {
		// TO DO
		return "/";
	}
	
	@GetMapping("/reservas/eliminar/{id}")
	public String eliminarReserva(@PathVariable("id") int idReserva) {
		reservaDaoImpl.cancelarReserva(reservaDaoImpl.buscarUnaReserva(idReserva));
		return "forward:/reservas/misReservas";
	}

}
