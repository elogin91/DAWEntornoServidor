package net.unir.eventos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.unir.eventos.modelo.dao.EventoDao;

@Controller
public class HomeController {

	// Busca una clase en tiempo de ejecución que implementar la interface y crea un
	// objeto (ClienteDao) singleton
	@Autowired
	private EventoDao edao;

	//Recibimos una petición inicial Get y devolvemos la vista correspondiente a la home
	@GetMapping("/")
	public String mostrarHome(Model model) {
		
		//TODO mostrar solo los activos
		model.addAttribute("eventos", edao.findAll());
		
		//Mostrar los cancelados
		return "index";
	}
}
