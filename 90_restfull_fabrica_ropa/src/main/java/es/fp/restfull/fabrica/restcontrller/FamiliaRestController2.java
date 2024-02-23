package es.fp.restfull.fabrica.restcontrller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.fp.restfull.fabrica.modelo.dao.FamiliaDao;
import es.fp.restfull.fabrica.modelo.entitybeans.Familia;
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/familias2")
public class FamiliaRestController2 {
	
	@Autowired
	private FamiliaDao fdao;
	
	@GetMapping("/todas")
	public List<Familia> procTodas(){
		return fdao.findAll();
	}
	
	@GetMapping("/verUna/{idFamilia}")
//	public ResponseEntity<Familia> procTodas(@PathVariable("idFamilia") int idFamilia){
	public Familia procTodas(@PathVariable("idFamilia") int idFamilia){
		
	/*	Familia familia = fdao.verUna(idFamilia);
		if (familia != null)
			return ResponseEntity.status(HttpStatus.OK).body(familia);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	*/		
		
		 	return fdao.verUna(idFamilia);
	}
	
	@PostMapping("/alta")
	public ResponseEntity<String> procAlta(@RequestBody Familia familia) {
		if (fdao.insertar(familia) == 0)
			return ResponseEntity.status(HttpStatus.OK).body("Alta realizada");
		else
			return ResponseEntity.status(HttpStatus.FOUND).body("Alta No realizada");
	//		return ResponseEntity.unprocessableEntity().build();
	}
	
	@PutMapping("/modificar")
	public String procModi(@RequestBody Familia familia) {
		return (fdao.modificar(familia) == 1)?"Modif realizada":"Modif NOOO realizada";
	}
	
	@DeleteMapping("/eliminar/{idFamilia}")
	public String procModi(@PathVariable("idFamilia") int idFamilia) {
		return (fdao.eliminar(idFamilia) == 1)?"Elim realizada":"Elim NOOO realizada";
	}

}
