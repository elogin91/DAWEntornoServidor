package es.teod.familiasec.modelo.beans;
import java.io.Serializable;
import jakarta.persistence.*;



/**
 * The persistent class for the familias database table.
 * 
 */
@Entity
@Table(name="familias")

public class Familia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;

	private String descripcion;

	 

	public Familia() {
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Familia [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Familia))
			return false;
		Familia other = (Familia) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	 

 

	 

	 

}