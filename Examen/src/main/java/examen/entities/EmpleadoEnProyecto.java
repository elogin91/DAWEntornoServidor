package examen.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


/**
 * The persistent class for the empleados_en_proyecto database table.
 */
@Entity
@Data
@Table(name="empleados_en_proyecto")
public class EmpleadoEnProyecto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_entrada")
	private int idEntrada;

	@Column(name="dias_previstos")
	private int diasPrevistos;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_incorporacion")
	private Date fechaIncorporacion;

	@ManyToOne
	@JoinColumn(name="id_empleado")
	private Empleado empleado;

	@ManyToOne
	@JoinColumn(name="id_proyecto")
	private Proyecto proyecto;
}
