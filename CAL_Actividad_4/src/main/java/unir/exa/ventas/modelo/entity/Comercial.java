package unir.exa.ventas.modelo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="comerciales")
public class Comercial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_comercial")
	private int idComercial;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int comision;

}
