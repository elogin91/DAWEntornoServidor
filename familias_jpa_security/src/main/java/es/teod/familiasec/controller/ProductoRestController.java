package es.teod.familiasec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.teod.familiasec.modelo.beans.Producto;
import es.teod.familiasec.modelo.dao.IntProductoDao;


@RestController
@RequestMapping("rest/producto")
public class ProductoRestController {
	
	@Autowired
	private IntProductoDao pdao;
	@GetMapping("/todos")
	public List<Producto> todos(){
		
		
		return pdao.buscarTodos();
	}

}
