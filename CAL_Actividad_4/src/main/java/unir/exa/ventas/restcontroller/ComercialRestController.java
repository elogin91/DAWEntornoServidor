package unir.exa.ventas.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unir.exa.ventas.modelo.entity.Comercial;
import unir.exa.ventas.modelo.entity.Pedido;
import unir.exa.ventas.modelo.service.ComercialDao;
import unir.exa.ventas.modelo.service.PedidoDao;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/comercial")
public class ComercialRestController {

	@Autowired
	ComercialDao comercialDao;
	@Autowired
	PedidoDao pedidoDao;

	@PostMapping("/alta")
	public ResponseEntity<?> creandoComercial(@RequestBody Comercial comercial) {
		if (comercialDao.altaComercial(comercial) != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(comercial);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alta no realizada");
		}
	}

	@DeleteMapping("eliminar/{id}")
	ResponseEntity<?> eliminandoComercial(@PathVariable int id) {
		if (comercialDao.eliminarComercial(id)) {
			return ResponseEntity.status(HttpStatus.OK).body("Comercial eliminado");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comercial no eliminado");
		}
	}

	@GetMapping("/uno/{id}")
	ResponseEntity<?> buscandoUnComercial(@PathVariable int id) {
		Comercial comercial = comercialDao.buscarUnComercial(id);
		if (comercial != null) {
			return ResponseEntity.status(HttpStatus.OK).body(comercial);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comercial no encontrado");
		}
	}

	@GetMapping("/bycliente/{id}")
	ResponseEntity<?> buscandoComercialPorCliente(@PathVariable int id) {
		List<Comercial> comerciales = comercialDao.buscarComercialPorCliente(id);
		if (!comerciales.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(comerciales);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comerciales no encontrados");
		}
	}

	@GetMapping("/conpedidos")
	ResponseEntity<?> buscandoComercialConPedidos() {
		List<Comercial> comerciales = comercialDao.buscarComercialesConPedidos();
		if (!comerciales.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(comerciales);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comerciales no encontrados");
		}
	}

	@GetMapping("/pedidos/{id}")
	ResponseEntity<?> buscandoPedidosPorComercial(@PathVariable int id) {
		List<Pedido> pedidos = pedidoDao.pedidosByComercial(id);
		if (!pedidos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(pedidos);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedidos no encontrados");
		}
	};
}
