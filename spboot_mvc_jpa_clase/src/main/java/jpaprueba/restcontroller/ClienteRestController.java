package jpaprueba.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jpaprueba.modelo.entitybean.Cliente;
import jpaprueba.modelodao.ClienteDao;

//Transforma mis objetos a JSON
@RestController
@RequestMapping("/apirest/clientes")
public class ClienteRestController {
	@Autowired
	private ClienteDao cdao;
	
	@GetMapping("/todos")
	public List<Cliente> todos(){
		return cdao.buscarTodos();
	}
	
	@GetMapping("/uno/{idCliente}")
	public Cliente buscarUno(@PathVariable int idCliente){
		return cdao.buscarUno(idCliente);
	}
	
	@GetMapping("/porfactura/{facturacionAnual}")
	public List <Cliente> buscarPorFacturacion(@PathVariable double facturacionAnual){
		return cdao.buscarPorFacturacionMayorQue(facturacionAnual);
	}
	
	@PostMapping("/alta")
	public Cliente alta(@RequestBody Cliente cliente){
		return cdao.insertarUno(cliente);
	}
	@DeleteMapping("/eliminar/{idCliente}")
	public String eliminar(@PathVariable int idCliente){
		if(cdao.borrarUno(idCliente) == 1) {
			return "cliente eliminado";
		}
		else {
			return "cliente NO eliminado";
		}
	}
	
	@PutMapping("/modificar")
	public String modificarUno(@RequestBody Cliente cliente){
		if(cdao.modificarUno(cliente) == 1) {
			return "cliente modificado";
		}
		else {
			return "cliente NO modificado";
		}
	}
}
