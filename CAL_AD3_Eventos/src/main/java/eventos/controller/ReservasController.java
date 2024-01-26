package eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import eventos.modelo.repository.ReservaRepository;

@Controller
public class ReservasController {
	@Autowired
	private ReservaRepository reservaRepository;
	
	@GetMapping("/reservas/misReservas")
	public String verMisReservas() {
		
		return "/misReservas";
	}
	
	@PostMapping("/reservas/alta")
	public String altaYModificarReserva() {
		// TO DO
		return "/";
	}

}
