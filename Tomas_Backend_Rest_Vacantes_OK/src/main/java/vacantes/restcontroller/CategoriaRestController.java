package vacantes.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vacantes.modelo.dto.CategoriaDto;
import vacantes.modelo.service.CategoriaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/categorias")
public class CategoriaRestController {
	
	@Autowired
	CategoriaService categoriaService;
	

	@GetMapping("/todas")
	public ResponseEntity<?> bucandoTodasCategorias(){
		List<CategoriaDto> categoriasDto = CategoriaDto.from(categoriaService.mostrarTodasCategorias());
		return ResponseEntity.status(201).body(categoriasDto);
	}

}
