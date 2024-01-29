package eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.ReservaDao;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;

@Controller
public class EventosController {

	@Autowired
	private EventoDao eventoDao;
	
	@Autowired
	private ReservaDao reservaDao;

	@GetMapping("/eventos/verUno/{id}")
	public String verUnEvento(@PathVariable("id") int idEvento, Model model, Authentication authentication) {
		Evento evento = eventoDao.buscarUnEvento(idEvento);
		if (evento != null) {
			model.addAttribute("evento", evento);
			if(authentication!= null) {
				List<Reserva> reservasDelUsuario = reservaDao.buscarReservasPorClienteYEvento(authentication.getName(), idEvento);
				model.addAttribute("cantidadReservas", reservasDelUsuario.stream().mapToInt(it -> it.getCantidad()).sum());
			}
			List<Reserva> reservasDelEvento = reservaDao.buscarReservasPorEvento(idEvento);
			model.addAttribute("aforoDisponible", evento.getAforoMaximo() - reservasDelEvento.stream().mapToInt(it -> it.getCantidad()).sum());
			return "detalleEvento";
		} else {
			model.addAttribute("mensaje", "El evento buscado no existe");
			return "forward:/";
		}
	}
	
	@GetMapping("/eventos/destacados")
	public String mostrarEventosDestacados(Model model) {
		model.addAttribute("eventos", eventoDao.buscarEventosDestacados());
		model.addAttribute("mensaje", "Lista de eventos destacados.");
		return "eventos";
	}
	
	@GetMapping("/eventos/activos")
	public String mostrarEventosActivos(Model model) {
		model.addAttribute("eventos", eventoDao.buscarEventosActivos());
		model.addAttribute("mensaje", "Lista de eventos activos.");
		return "eventos";
	}

}
