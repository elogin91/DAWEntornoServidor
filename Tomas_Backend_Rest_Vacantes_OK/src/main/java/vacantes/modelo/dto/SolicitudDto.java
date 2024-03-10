package vacantes.modelo.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;
import vacantes.modelo.entidades.Solicitud;


@Data
public class SolicitudDto {
	private int idSolicitud;
	private String nombre;
	private String archivo;
	private String comentario;
	private int estado;
	private Date fecha;
	private int idVacante;
	
	public static SolicitudDto from(Solicitud solicitud) {
		SolicitudDto solicitudDto = new SolicitudDto();
		solicitudDto.setIdSolicitud(solicitud.getIdSolicitud());
		solicitudDto.setNombre(solicitud.getUsuario().getNombre());
		solicitudDto.setArchivo(solicitud.getArchivo());
		solicitudDto.setComentario(solicitud.getComentarios());
		solicitudDto.setEstado(solicitud.getEstado());
		solicitudDto.setFecha((Date) solicitud.getFecha());
		solicitudDto.setIdVacante(solicitud.getVacante().getIdVacante());
		return solicitudDto;
	}
	public static List<SolicitudDto> from(List<Solicitud> solicitudes) {
		return solicitudes.stream().map(SolicitudDto::from).toList();
	}
}
