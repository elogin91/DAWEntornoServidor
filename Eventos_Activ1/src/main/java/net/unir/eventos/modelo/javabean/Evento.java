package net.unir.eventos.modelo.javabean;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "idEvento")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idEvento;
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private int duracion;
	private String direccion;
	private Estado estado;
	private Character destacado;
	private int aforoMaximo;
	private int minimoAsistencia;
	private double precio;
	Tipo tipo;
}
