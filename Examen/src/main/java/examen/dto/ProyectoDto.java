package examen.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ProyectoDto {
	private Integer idProyecto;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer diasPrevistos;
	private String estado;	
}
