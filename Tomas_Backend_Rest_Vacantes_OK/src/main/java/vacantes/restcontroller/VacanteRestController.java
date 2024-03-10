package vacantes.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vacantes.modelo.dto.VacanteDto;
import vacantes.modelo.entidades.Vacante;
import vacantes.modelo.service.SolicitudService;
import vacantes.modelo.service.VacanteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vacantes")
public class VacanteRestController {

	@Autowired
	private VacanteService vacanteService;
	@Autowired
	private SolicitudService solicitudService;

	@PostMapping("/altaVacante")
	public ResponseEntity<?> alta(@RequestBody VacanteDto vacanteDto) {
		if (vacanteService.altaVacante(vacanteDto) != null) {
			return ResponseEntity.status(201).body(vacanteDto);
		} else {
			String mensaje = "Alta NOOO realizada";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
		}
	}

	@GetMapping("/")
	public ResponseEntity<?> bucandoVacantesActiva() {
		List<VacanteDto> vacantesDto = VacanteDto.from(vacanteService.buscarPorVacantesActivas());
		return ResponseEntity.status(201).body(vacantesDto);
	}
	
	@GetMapping("/buscar/{categoria}")
	public ResponseEntity<?> bucandoVacantesActivasPorCategoria(@PathVariable int categoria) {
		List<VacanteDto> vacantesDto = VacanteDto.from(vacanteService.buscarPorVacantesActivasPorCategoria(categoria));
		return ResponseEntity.status(201).body(vacantesDto);
	}

	@GetMapping("/verDetalle/{id}")
	public ResponseEntity<?> buscandoUnaVacante(@PathVariable int id, Authentication authentication) {
		Vacante vacante = vacanteService.buscarUnaVacante(id);

		if (vacante != null) {
			VacanteDto vacanteDto = VacanteDto.from(vacante);
			if (authentication != null
					&& solicitudService.bucarUnaSolicitudUsuarioYVacante(authentication.getName(), id) != null) {
				vacanteDto.setExisteSolicitud(true);
			}
			return ResponseEntity.status(HttpStatus.OK).body(vacanteDto);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacante no encontrada");
		}
	}

	@PostMapping("/cancelar/{id}")
	public ResponseEntity<?> cancelandoUnaVacante(@PathVariable int id) {
		Vacante vacante = vacanteService.buscarUnaVacante(id);
		if (vacante != null) {
			vacanteService.cancelarVacante(id);
			return ResponseEntity.status(HttpStatus.OK).body(vacante);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacante no encontrada");
		}
	}

	@PostMapping("/adjudicar/{id}")
	public ResponseEntity<?> adjudicandoUnaVacante(@PathVariable int id) {
		Vacante vacante = vacanteService.buscarUnaVacante(id);
		if (vacante != null) {
			vacanteService.adjudicarVacante(id);
			return ResponseEntity.status(HttpStatus.OK).body(vacante);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vacante no encontrada");
		}
	}

	@PostMapping("/modificandoVacante")
	public ResponseEntity<?> modificandoVacante(@RequestBody VacanteDto vacanteDto) {
		if (vacanteService.modificarVacante(vacanteDto) != null) {
			return ResponseEntity.status(201).body(vacanteDto);
		} else {
			String mensaje = "Alta NOOO realizada";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
		}
	}
}
