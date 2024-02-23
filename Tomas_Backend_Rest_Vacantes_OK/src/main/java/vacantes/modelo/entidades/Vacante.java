package vacantes.modelo.entidades;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the vacantes database table.
 * 
 */
@Entity
@Table(name="vacantes")
public class Vacante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_vacante")
	private int idVacante;

	private String descripcion;

	private int destacado;

	private String detalles;
	@Enumerated(value = EnumType.STRING)
	private EstatusVacante estatus;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String imagen;

	private String nombre;

	private double salario;

	//uni-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="id_Categoria")
	private Categoria categoria;

	public Vacante() {
	}

	public int getIdVacante() {
		return this.idVacante;
	}

	public void setIdVacante(int idVacante) {
		this.idVacante = idVacante;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDestacado() {
		return this.destacado;
	}

	public void setDestacado(int destacado) {
		this.destacado = destacado;
	}

	public String getDetalles() {
		return this.detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	

	public EstatusVacante getEstatus() {
		return estatus;
	}

	public void setEstatus(EstatusVacante estatus) {
		this.estatus = estatus;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSalario() {
		return this.salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idVacante;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vacante))
			return false;
		Vacante other = (Vacante) obj;
		if (idVacante != other.idVacante)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vacante [idVacante=" + idVacante + ", descripcion=" + descripcion + ", destacado=" + destacado
				+ ", detalles=" + detalles + ", estatus=" + estatus + ", fecha=" + fecha + ", imagen=" + imagen
				+ ", nombre=" + nombre + ", salario=" + salario + ", categoria=" + categoria + "]";
	}

	
	
	

}