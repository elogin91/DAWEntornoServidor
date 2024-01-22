package es.teod.familiasec.modelo.beans;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the perfiles database table.
 * 
 */
@Entity
@Table(name="perfiles")
@NamedQuery(name="Perfile.findAll", query="SELECT p FROM Perfile p")
public class Perfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PERFIL")
	private int idPerfil;

	private String nombre;

	public Perfile() {
	}

	public int getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}