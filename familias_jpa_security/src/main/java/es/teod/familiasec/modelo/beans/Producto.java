package es.teod.familiasec.modelo.beans;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;

	private String color;

	private String descripcion;

	private String marca;

	@Column(name="precio_unitario")
	private BigDecimal precioUnitario;

	//bi-directional many-to-one association to Familia
	@ManyToOne
	@JoinColumn(name="codigo_familia")
	private Familia familia;

	public Producto() {
	}
	
	

	public Producto(int codigo, String color, String descripcion, String marca, BigDecimal precioUnitario) {
		super();
		this.codigo = codigo;
		this.color = color;
		this.descripcion = descripcion;
		this.marca = marca;
		this.precioUnitario = precioUnitario;
	}



	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Familia getFamilia() {
		return this.familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", color=" + color + ", descripcion=" + descripcion + ", marca=" + marca
				+ ", precioUnitario=" + precioUnitario + ", familia=" + familia + "]";
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
		if (!(obj instanceof Producto))
			return false;
		Producto other = (Producto) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	

}