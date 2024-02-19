package unir.familias.modelo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="familias")
public class Familia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_familia")
	private int idFamilia;
	private String descripcion;

}
