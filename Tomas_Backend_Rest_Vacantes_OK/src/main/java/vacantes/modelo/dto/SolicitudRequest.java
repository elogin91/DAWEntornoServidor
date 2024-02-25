package vacantes.modelo.dto;

import java.sql.Date;

import lombok.Data;
import vacantes.modelo.entidades.Vacante;

@Data
public class SolicitudRequest {
	private String archivo;
	private String comentario;
	private int estado;
	private Date fecha;
	private Vacante vacante;
}
