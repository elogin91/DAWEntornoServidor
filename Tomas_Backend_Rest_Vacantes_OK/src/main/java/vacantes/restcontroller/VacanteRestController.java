package vacantes.restcontroller;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vacantes.modelo.dto.VacanteDto;
import vacantes.modelo.entidades.Vacante;
import vacantes.modelo.repository.VacanteRepository;
import vacantes.modelo.service.VacanteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vacantes")
public class VacanteRestController {
	
	@Autowired
	private VacanteRepository vrepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private VacanteService vacanteService;
	
	@PostMapping("/")
	public ResponseEntity<?> alta(@RequestBody VacanteDto vacanteDto){
		//vacanteDto.setEstatus(EstatusVacante.CREADA);
		Vacante vacante = new Vacante();
		modelMapper.map(vacanteDto, vacante);
		if (vrepo.save(vacante) != null) {
			//vacanteDto.setIdVacante(vacante.getIdVacante());
			return ResponseEntity.status(201).body(vacanteDto);
		}else {
			String mensaje = "Alta NOOO realizada";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
		}
			
	}
	@GetMapping("/")
	public ResponseEntity<?> bucandoTodasVacantes(){
		List<VacanteDto> vacantesDto = VacanteDto.from(vacanteService.mostrarTodasVacantes());
		return ResponseEntity.status(201).body(vacantesDto);
	}
}
