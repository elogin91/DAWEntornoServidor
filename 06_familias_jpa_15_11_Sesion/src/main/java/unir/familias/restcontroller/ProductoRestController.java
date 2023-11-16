package unir.familias.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import unir.familias.modelo.dao.ProductoDao;
import unir.familias.modelo.entity.Producto;

@RestController
public class ProductoRestController {
	
	@Autowired
	private ProductoDao pdao;
	
	@PostMapping("/alta")
	public Producto alta(@RequestBody Producto producto) {
		
		return pdao.insertOne(producto);
	}
	
	
	@GetMapping("/todos")
	
	public List<Producto> todos(){
		return pdao.todos();
		
	}
	
	@GetMapping("/uno/{idProducto}")
	public Producto uno(@PathVariable int idProducto){
		Producto producto = pdao.buscarUno(idProducto);
		System.out.println("descripcion familia : " + producto.getFamilia().getDescripcion());
		return producto;
		
	}
	
	@GetMapping("/porfamilia/{idFamilia}")
	public List<Producto> porfamilia(@PathVariable int idFamilia){
		
		return pdao.buscarProductosPorFamilia(idFamilia);
		
	}
	
	
	

}
