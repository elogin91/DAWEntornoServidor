package cajero.modelo.entitybean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="cuentas")
public class Cuenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cuenta")
	private int idCuenta;
	@Column(name="saldo")
	private Double saldo;
	@Column(name="tipo_cuenta")
	private String tipoCuenta;
}
