package net.unir.eventos.modelo.javabean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tipo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idTipo;
	private String nombre;
	private String descripcion;

}
