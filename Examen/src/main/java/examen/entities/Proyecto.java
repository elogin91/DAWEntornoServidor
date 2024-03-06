package examen.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Objects;


/**
 * The persistent class for the proyectos database table.
 */
@Entity
@Data
@Table(name="proyectos")
public class Proyecto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proyecto")
	private int idProyecto;

	private String descripcion;

	@Column(name="dias_previstos")
	private int diasPrevistos;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	@ManyToOne
	@JoinColumn(name="director")
	private Empleado empleado;
}
