package vacantes.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vacantes.modelo.dto.SolicitudRequest;
import vacantes.modelo.entidades.Solicitud;
import vacantes.modelo.repository.UsuarioRepository;
import vacantes.modelo.service.SolicitudService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/solicitudes")
public class SolicitudRestController {
	@Autowired
	private SolicitudService solicitudService;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping("/alta")
	public ResponseEntity<?> creandoSolicitud(@RequestBody SolicitudRequest solicitudRequest, Authentication auth){
		Solicitud solicitud =solicitudService.handlerSolicitudRequest(solicitudRequest, auth);
		if (solicitudService.altaSolicitud(solicitud) != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(solicitud.getIdSolicitud());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitud no realizada");
		}
	}
	
	@GetMapping("/verTodas")
	public ResponseEntity<?> buscandoSolicitudesPorUsuario(Authentication auth) {
		List<Solicitud> solicitudes = solicitudService.buscarTodasSolicitudesPorUsuario(usuarioRepository.findByUsername(auth.getName()).get());
		if (!solicitudes.isEmpty()) {
			return ResponseEntity.ok(solicitudes);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitudes no encontrada");
		}
	}
}
