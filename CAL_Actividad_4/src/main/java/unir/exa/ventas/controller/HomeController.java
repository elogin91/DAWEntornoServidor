package unir.exa.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import unir.exa.ventas.modelo.entity.Comercial;
import unir.exa.ventas.modelo.service.ClienteDaoImpl;
import unir.exa.ventas.modelo.service.ComercialDaoImpl;
import unir.exa.ventas.modelo.service.PedidoDaoImpl;

@Controller
public class HomeController {

	@Autowired
	ComercialDaoImpl comdao;
	@Autowired
	ClienteDaoImpl clidao;
	@Autowired
	PedidoDaoImpl peddao;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("comerciales", comdao.listarTodos());
		return "home";
	}

	@GetMapping("/clientes")
	public String cliente(Model model) {
		model.addAttribute("clientes", clidao.listarClientes());
		return "clientes";
	}

	@GetMapping("/pedidos")
	public String pedido(Model model) {
		model.addAttribute("pedidos", peddao.listarTodoPedidos());
		return "pedidos";
	}

	@GetMapping("/detalleComercial/{idComercial}")
	public String pedidoPorComercial(Model model, @PathVariable int idComercial) {
		model.addAttribute("pedidos", peddao.pedidosByComercial(idComercial));
		return "detalleComercial";
	}

	@GetMapping("/detalleCliente/{idCliente}")
	public String pedidoPorCliente(Model model, @PathVariable int idCliente) {
		model.addAttribute("pedidos", peddao.pedidosByCliente(idCliente));
		return "detalleCliente";
	}

	@GetMapping("/altaComercial")
	public String altaComercial(Model model) {
		model.addAttribute("comercial", new Comercial());
		return "altaComercial";
	}

	@PostMapping("/altaComercial")
	public String procesandoAltaComercial(RedirectAttributes ratt, Comercial comercial) {
		if (comdao.altaComercial(comercial) == null) {
			ratt.addFlashAttribute("mensaje", "Algo ha fallado, alta no realizada");
		} else {
			ratt.addFlashAttribute("mensaje", "Comercial dado de alta");
		}
		return "redirect:/";
	}
}