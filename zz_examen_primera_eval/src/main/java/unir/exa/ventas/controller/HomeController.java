package unir.exa.ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import unir.exa.ventas.modelo.dao.ClienteDaoImpl;
import unir.exa.ventas.modelo.dao.ComercialDaoImpl;
import unir.exa.ventas.modelo.dao.PedidoDaoImpl;

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
	public String pedidoPorComercial(Model model, @PathVariable  int idComercial) {
		model.addAttribute("pedidos", peddao.pedidosByComercial(idComercial));
		return "detalleComercial";
	}
	@GetMapping("/detalleCliente/{idCliente}")
	public String pedidoPorCliente(Model model, @PathVariable  int idCliente) {
		model.addAttribute("pedidos", peddao.pedidosByCliente(idCliente));
		return "detalleCliente";
	}
}