package vacantes.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vacantes.modelo.dto.VacanteDto;
import vacantes.modelo.entidades.Vacante;
import vacantes.modelo.service.VacanteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/public")
public class PublicController {
	@Autowired
	private VacanteService vacanteService;
	
	@GetMapping("/")
	public ResponseEntity<?> bucandoTodasVacantes(){
		List<VacanteDto> vacantesDto = VacanteDto.from(vacanteService.mostrarTodasVacantes());
		return ResponseEntity.status(201).body(vacantesDto);
	}
	
	@GetMapping("/verDetalle/{id}")
	public ResponseEntity<?> buscandoUnaVacante(@PathVariable int id){
		Vacante vacante = vacanteService.buscarUnaVacante(id);
		if(vacante != null) {
			return ResponseEntity.status(HttpStatus.OK).body(vacante);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacante no encontrada");
		}
	}
}
