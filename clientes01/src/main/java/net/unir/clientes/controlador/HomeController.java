package net.unir.clientes.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.unir.clientes.modelo.dao.ClienteDao;

@Controller
public class HomeController {

	// Busca una clase en tiempo de ejecución que implementar la interface y crea un
	// objeto (ClienteDao) singleton
	@Autowired
	private ClienteDao cdao;

	@GetMapping("/")
	public String mostrarHome(Model model) {
		model.addAttribute("clientes", cdao.findAll());
		return "home";
	}
}
