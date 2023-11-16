package cajero.modelo.entitybean;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Data
@Entity
@Table(name="movimientos")
public class Movimiento {
	
	@Column(name="id_movimiento")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMovimiento;
	@Column(name="id_cuenta")
	private int idCuenta;
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(name="cantidad")
	private Double cantidad;
	@Column(name="operacion")
	private String operacion;
	
}
