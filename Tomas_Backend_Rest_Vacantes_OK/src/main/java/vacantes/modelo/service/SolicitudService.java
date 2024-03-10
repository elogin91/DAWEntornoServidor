package vacantes.modelo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import vacantes.modelo.dto.SolicitudRequest;
import vacantes.modelo.entidades.Solicitud;
import vacantes.modelo.entidades.Usuario;
import vacantes.modelo.entidades.Vacante;
import vacantes.modelo.repository.SolicitudRepository;
import vacantes.modelo.repository.UsuarioRepository;


@Service
public class SolicitudService {
	@Autowired
	SolicitudRepository solicitudRepository;
	@Autowired
	VacanteService vacanteService;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Solicitud altaSolicitud (Solicitud solicitud) {
		try {
		if (solicitudRepository.findById(solicitud.getIdSolicitud()).isEmpty()) {
			return solicitudRepository.save(solicitud);
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	return null;
	}
	public List<Solicitud> buscarTodasSolicitudesPorUsuario(Usuario usuario) {
		return solicitudRepository.findByUsuario(usuario);
	}

	public Solicitud handlerSolicitudRequest(SolicitudRequest solicitudRequest, Authentication auth) {
		Solicitud solicitudMap = new Solicitud();
		solicitudMap.setArchivo(solicitudRequest.getArchivo());
		solicitudMap.setComentarios(solicitudRequest.getComentario());
		solicitudMap.setFecha(solicitudRequest.getFecha());
		solicitudMap.setUsuario(usuarioRepository.findByUsername(auth.getName()).get());
		solicitudMap.setVacante(solicitudRequest.getVacante());
		return solicitudMap;
	}
	
	public List<Solicitud> buscarTodasSolicitudesPorVacante(int idVacante){
		Vacante vacante = vacanteService.buscarUnaVacante(idVacante);
		return solicitudRepository.findByVacante(vacante);
	}
	
	public Solicitud bucarUnaSolicitud (int idSolicitud) {
		Solicitud solicitud = solicitudRepository.findById(idSolicitud).orElse(null);
		return solicitud;
	}
	public Solicitud bucarUnaSolicitudUsuarioYVacante (String username, int idVacante) {
		Solicitud solicitud = solicitudRepository.findByUsuarioAndVacante(username, idVacante);
		return solicitud;
	}
	
	public Solicitud modificarSolicitud(Solicitud solicitud) {
		return solicitudRepository.save(solicitud);
	}
	
	public Solicitud adjudicarSolicitud (Solicitud solicitud) {
		solicitud.setEstado(1);
		return solicitudRepository.save(solicitud);
	}
	public Solicitud cancelarSolicitud (Solicitud solicitud) {
		solicitud.setEstado(2);
		return solicitudRepository.save(solicitud);
	}
}
