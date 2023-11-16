package unir.familias.modelo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	private String username;
	private String password;
	private String nombre;
	private String apellidos;
	@Column(name="created_at")
	@Temporal(TemporalType.TIME)
	private Date credatedAt;
	private int enabled;
	
	
	

}
