package examen.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmpleadoDto {
	private String nombre;
	private Date fechaInicio;
	private Integer diasAsignados;
}
