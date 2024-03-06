package examen.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProyectoConEmpleadosDto {
	private Integer idProyecto;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer diasPrevistos;
	private String estado;
	private List<EmpleadoDto> empleados;

}
