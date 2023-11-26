package cajero.modelo.entitybean;

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
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name="movimientos")
public class Movimiento {
	
	@Column(name="id_movimiento")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMovimiento;
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta cuenta;
	@Column(name="fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Column(name="cantidad")
	private Double cantidad;
	@Column(name="operacion")
	private String operacion;
	
}
